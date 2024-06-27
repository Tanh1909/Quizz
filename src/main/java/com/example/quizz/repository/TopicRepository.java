package com.example.quizz.repository;

import com.example.quizz.entity.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
        List<Topic> findAllByUserUsername(String username);
        List<Topic> findAllByCategoryName(String name, Pageable pageable);
        List<Topic> findAllByCategoryName(String name);

}
