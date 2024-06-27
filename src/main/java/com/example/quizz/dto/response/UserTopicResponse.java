package com.example.quizz.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserTopicResponse {
    private Long id;
    private UserResponse user;
    private TopicResponseDTO topic;
    private List<UserQuestionResponse> answers=new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
