package com.example.quizz.controller;

import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.entity.FeedBack;
import com.example.quizz.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FeedBack feedBack){
        return ResponseEntity.ok(ResponseApi.success(feedBackService.create(feedBack)));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ResponseApi.success(feedBackService.findAll()));
    }

}
