package com.example.quizz.service.serviceImpl;


import com.example.quizz.entity.Category;
import com.example.quizz.repository.CategoryRepository;
import com.example.quizz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<String> findAllNonTopics() {
        return categoryRepository.findAllNonTopic();

    }
}
