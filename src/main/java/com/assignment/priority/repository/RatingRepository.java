package com.assignment.priority.repository;

import com.assignment.priority.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findAllByCategoryId(Integer categoryId);

}
