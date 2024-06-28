package com.example.quizz.repository;

import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
        List<Topic> findAllByUserUsername(String username);
        List<Topic> findAllByCategoryName(String name, Pageable pageable);
        List<Topic> findAllByCategoryName(String name);

        @Query("SELECT new com.example.quizz.dto.response.TopicResponseDTO(t.id, t.name) " +
                "FROM Topic t WHERE t.name LIKE %:name%")
        List<TopicResponseDTO> findAllByTopicNameContains(String name, Pageable pageable);

        @Query("SELECT new com.example.quizz.dto.response.TopicResponseDTO(t.id, t.name) " +
                "FROM Topic t WHERE t.name LIKE %:name%")
        List<TopicResponseDTO> findAllByNameContaining(String name);
}
