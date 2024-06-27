package com.example.quizz.dto.request;

import com.example.quizz.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class TopicRequest {
    String category;
    String name;
    MultipartFile image;
    List<QuestionRequest> questions;
}
