package com.example.quizz.controller;

import com.example.quizz.dto.request.UserTopicRequest;
import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.service.UserTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private UserTopicService userTopicService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserTopicRequest userTopicRequest){
            return ResponseEntity.ok(ResponseApi.success(userTopicService.create(userTopicRequest)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return ResponseEntity.ok(ResponseApi.success(userTopicService.findById(id)));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable long id){
        return ResponseEntity.ok(ResponseApi.success(userTopicService.findByUser(id)));
    }
}
