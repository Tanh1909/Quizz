package com.example.quizz.controller;

import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/nonTopics")
    public ResponseEntity<?> findAllNonTopic(){
        return ResponseEntity.ok(ResponseApi.success(categoryService.findAllNonTopics()));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ResponseApi.success(categoryService.findAll()));
    }
}
