package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.Category;
import com.elearningweb.library.repository.CategoryRepository;
import com.elearningweb.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        Category categorySave = new Category(category.getName());
        return categoryRepository.save(categorySave);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
