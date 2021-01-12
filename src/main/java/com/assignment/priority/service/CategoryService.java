package com.assignment.priority.service;

import com.assignment.priority.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(Category category);

}
