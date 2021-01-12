package com.assignment.priority.controller;

import com.assignment.priority.dto.RatingDto;
import com.assignment.priority.entity.Rating;
import com.assignment.priority.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("categories/ratings")
    public List<Rating> createAllRatings(@NonNull @Valid @RequestBody List<RatingDto> ratings) {
        return ratingService.createRatingsForAllCategories(ratings);
    }

    @PostMapping("categories/{categoryId}/ratings")
    public Rating createRatingByCategoryId(@PathVariable Integer categoryId,
                               @NonNull @Valid @RequestBody Rating rating) {
        return ratingService.createRatingByCategoryId(categoryId, rating);
    }

    @GetMapping("categories/ratings")
    public Map<String, List<Rating>> getAllRatings() {
        return ratingService.getAllRatingsGroupedByCategory();
    }

    @GetMapping("categories/{categoryId}/ratings")
    public List<Rating> getAllRatingsByCategoryId(@PathVariable Integer categoryId) {
        return ratingService.getAllRatingsByCategoryId(categoryId);
    }

}
