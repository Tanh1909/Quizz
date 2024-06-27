package com.example.quizz.service.serviceImpl;


import com.example.quizz.constants.PaginationConstants;
import com.example.quizz.entity.Category;
import com.example.quizz.mapper.CategoryMapper;
import com.example.quizz.repository.CategoryRepository;
import com.example.quizz.repository.TopicRepository;
import com.example.quizz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories=categoryMapper.toCategories(categoryRepository.findAllCustom());
        categories.forEach(category -> {
            Pageable pageable= PageRequest.of(PaginationConstants.DEFAULT_PAGE.getValue(), PaginationConstants.DEFAULT_SIZE.getValue());
            category.setTopics(topicRepository.findAllByCategoryName(category.getName(),pageable));
        });
        return categories;
    }

    @Override
    public List<String> findAllNonTopics() {
        return categoryRepository.findAllNonTopic();

    }
}
