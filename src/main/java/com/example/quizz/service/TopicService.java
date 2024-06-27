package com.example.quizz.service;

import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.Topic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TopicService {
    List<TopicResponseDTO> findByUser(String username);
    Topic create(TopicRequest topicRequest);
    List<Topic> create(List<Topic> topics);
    Topic findById(Long id);
    List<TopicResponseDTO> findAll();
    Topic addQuestion(Long id,List<QuestionRequest> questions);
    Topic patchTopic(Long id,TopicRequest topicRequest);
    String uploadImage(Long id, MultipartFile image);
    void delete(Long id);
    List<Topic> findAllByCategory(String category,Integer page,Integer size);



}
