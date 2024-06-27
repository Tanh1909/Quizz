package com.example.quizz.service;


import com.example.quizz.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> findAll();
    List<String> findAllNonTopics();
}
