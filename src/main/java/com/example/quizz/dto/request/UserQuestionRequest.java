package com.example.quizz.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionRequest {
    private Long questionId;
    private Integer answer;
}
