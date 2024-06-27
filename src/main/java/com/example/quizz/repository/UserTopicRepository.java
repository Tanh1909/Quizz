package com.example.quizz.repository;

import com.example.quizz.entity.UserTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTopicRepository extends JpaRepository<UserTopic,Long> {
    List<UserTopic> findByUserId(Long id);
}
