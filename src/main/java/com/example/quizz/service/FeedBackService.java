package com.example.quizz.service;

import com.example.quizz.entity.FeedBack;

import java.util.List;

public interface FeedBackService {
    FeedBack create(FeedBack feedBackReq);
    List<FeedBack> findAll();
}
