package com.example.quizz.service;

import com.example.quizz.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer create(Answer answer);
    Answer findById(Long id);
    void deleteById(Long id);
    List<Answer> findAll();
}
