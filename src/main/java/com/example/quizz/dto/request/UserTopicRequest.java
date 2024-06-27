package com.example.quizz.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTopicRequest {
    private Long topicId;
    private List<UserQuestionRequest> answers;
}
