package com.example.quizz.repository;

import com.example.quizz.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
