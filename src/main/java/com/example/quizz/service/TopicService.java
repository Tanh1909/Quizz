package com.example.quizz.service;

import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.Question;
import com.example.quizz.entity.Topic;

import java.util.List;

public interface TopicService {
    Topic create(TopicRequest topicRequest);
    List<Topic> create(List<Topic> topics);
    Topic findById(Long id);
    void deleteById(Long id);
    List<TopicResponseDTO> findAll();
    Topic addQuestion(Long id,List<QuestionRequest> questions);
}
