package com.example.quizz.repository;

import com.example.quizz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTopicId(Long id);
}
