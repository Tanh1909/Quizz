package com.example.quizz.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {
    private String question;
    private List<String> answers;
    private int correctAnswer;
}
