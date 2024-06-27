package com.example.quizz.repository;

import com.example.quizz.dto.response.CategoryResponse;
import com.example.quizz.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
    @Query("SELECT c.name from Category c")
    List<String> findAllNonTopic();

    @Query("select new com.example.quizz.dto.response.CategoryResponse(c.name) from Category c")
    List<CategoryResponse> findAllCustom();
}
