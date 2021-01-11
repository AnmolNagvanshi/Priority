package com.assignment.priority.service;

import com.assignment.priority.entity.Category;
import com.assignment.priority.exception.BadRequestException;
import com.assignment.priority.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new BadRequestException("category with given name already exists");
        return categoryRepository.save(category);
    }

}
