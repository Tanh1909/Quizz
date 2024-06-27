package com.example.quizz.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionResponse {
    private Long questionId;
    private Integer answer;
}
