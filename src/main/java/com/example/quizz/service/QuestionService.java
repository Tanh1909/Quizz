package com.example.quizz.service;

import com.example.quizz.entity.Answer;
import com.example.quizz.entity.Question;

import java.util.List;

public interface QuestionService {
    Question create(Question question);
    Question findById(Long id);
    void deleteById(Long id);
    List<Question> findAll();
    Question addAnswers(Long id, List<Answer> answers);
    List<Question> findByTopicId(Long id);
}
