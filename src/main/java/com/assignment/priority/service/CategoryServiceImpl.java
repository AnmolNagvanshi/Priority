package com.assignment.priority.service;

import com.assignment.priority.entity.Category;
import com.assignment.priority.exception.BadRequestException;
import com.assignment.priority.exception.ResourceNotFoundException;
import com.assignment.priority.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    @Override
//    public Category getCategoryById(Integer id) {
//        Optional<Category> category = categoryRepository.findById(id);
//        if(category.isEmpty())
//            throw new ResourceNotFoundException("category does not exist");
//        return category.get();
//    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

}
