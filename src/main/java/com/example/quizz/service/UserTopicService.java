package com.example.quizz.service;

import com.example.quizz.dto.request.UserTopicRequest;
import com.example.quizz.dto.response.UserTopicResponse;
import com.example.quizz.entity.UserTopic;

import java.util.List;

public interface UserTopicService {
    UserTopicResponse create(UserTopicRequest userTopicRequest);
    UserTopicResponse findById(Long id);
    List<UserTopicResponse> findByUser(Long id);
}
