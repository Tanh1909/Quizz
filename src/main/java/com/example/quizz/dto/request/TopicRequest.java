package com.example.quizz.dto.request;

import com.example.quizz.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopicRequest {
    String name;
    List<Question> questions;
}
