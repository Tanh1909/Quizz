package com.example.quizz.controller;

import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.entity.Answer;
import com.example.quizz.entity.Question;
import com.example.quizz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Question question){
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseApi.success(questionService.create(question)));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ResponseApi.success(questionService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseApi.success(questionService.findById(id)));
    }
    @PostMapping("/{id}")
    public ResponseEntity addAnswers(@PathVariable Long id, List<Answer> answers){
        return ResponseEntity.ok(ResponseApi.success(questionService.addAnswers(id,answers)));
    }

}
