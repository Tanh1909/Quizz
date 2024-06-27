package com.example.quizz.repository;

import com.example.quizz.dto.response.CategoryResponseNonTopics;
import com.example.quizz.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    @Query("SELECT c.name from Category c")
    List<String> findAllNonTopic();
}
