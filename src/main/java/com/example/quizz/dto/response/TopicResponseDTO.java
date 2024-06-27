package com.example.quizz.dto.response;

import com.example.quizz.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDTO {
    private Long id;
    private String name;
    private String image;
    private String category;
    private List<Question> questions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
