package com.elearningweb.library.service;

import com.elearningweb.library.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category getByName(String name);

}
