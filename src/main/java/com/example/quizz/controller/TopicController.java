package com.example.quizz.controller;

import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.entity.Question;
import com.example.quizz.entity.Topic;
import com.example.quizz.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TopicRequest topicRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseApi.success(topicService.create(topicRequest)));
    }
    @PostMapping("/batch")
    public ResponseEntity<?> createAll(@RequestBody List<Topic> topics){
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseApi.success(topicService.create(topics)));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ResponseApi.success(topicService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseApi.success(topicService.findById(id)));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> addQuestion(@PathVariable Long id,@RequestBody List<QuestionRequest> questions){
        return ResponseEntity.ok(ResponseApi.success(topicService.addQuestion(id,questions)));
    }
}
