package com.assignment.priority.service;

import com.assignment.priority.dto.RatingDto;
import com.assignment.priority.entity.Rating;

import java.util.List;
import java.util.Map;

public interface RatingService {

    Rating createRatingByCategoryId(Integer categoryId, Rating rating);

    List<Rating> createRatingsForAllCategories(List<RatingDto> ratings);

    List<Rating> getAllRatingsByCategoryId(Integer categoryId);

    Map<String, List<Rating>> getAllRatingsGroupedByCategory();
}
