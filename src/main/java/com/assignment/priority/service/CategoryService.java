package com.assignment.priority.service;

import com.assignment.priority.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

//    Category getCategoryById(Integer id);

    Category createCategory(Category category);

}
