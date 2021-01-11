package com.assignment.priority.service;

import com.assignment.priority.dto.RatingDto;
import com.assignment.priority.entity.Category;
import com.assignment.priority.entity.Rating;
import com.assignment.priority.exception.BadRequestException;
import com.assignment.priority.exception.ResourceNotFoundException;
import com.assignment.priority.repository.CategoryRepository;
import com.assignment.priority.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository,
                             CategoryRepository categoryRepository) {
        this.ratingRepository = ratingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Rating createRatingByCategoryId(Integer categoryId, Rating rating) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category with given id does not exist"));

        long numOfCategories = categoryRepository.count();
        int priorityOrder = rating.getPriorityOrder();
        validatePriorityOrder(priorityOrder, numOfCategories);

        rating.setCategory(category);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> createAllRatings(List<RatingDto> ratings) {
        validateAllPriorityOrders(ratings);

        List<Category> categories = categoryRepository.findAll();
        Map<Integer, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
        }

        List<Rating> newRatings = new ArrayList<>();
        for (RatingDto rating : ratings) {
            newRatings.add(new Rating(
                    categoryMap.get(rating.getCategoryId()),
                    rating.getPriorityOrder(),
                    rating.getSatisfactionRating()
            ));
        }

        return ratingRepository.saveAll(newRatings);
    }

    @Override
    public List<Rating> getAllRatingsByCategoryId(Integer categoryId) {
        return ratingRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Map<String, List<Rating>> getAllRatings() {
        List<Category> categories = categoryRepository.findAll();
        Map<String, List<Rating>> categoryToRatings = new HashMap<>();
        for (Category category : categories) {
            categoryToRatings.put(category.getName(), category.getRatings());
        }
        return categoryToRatings;
    }

    private void validateAllPriorityOrders(List<RatingDto> ratings) {
        long numOfCategories = categoryRepository.count();
        Set<Integer> set = new HashSet<>();
        for (RatingDto rating : ratings) {
            int priorityOrder = rating.getPriorityOrder();
            validatePriorityOrder(priorityOrder, numOfCategories);
            if (set.contains(priorityOrder))
                throw new BadRequestException("priority order of all categories should be unique");
            set.add(priorityOrder);
        }
    }

    private void validatePriorityOrder(int priorityOrder, long numOfCategories) {
        if (priorityOrder < 1 || priorityOrder > numOfCategories) {
            String message = "priority order for the category must lie between 1 and " + numOfCategories;
            throw new BadRequestException(message);
        }
    }

}
