package com.example.FeedBack.repository;

import com.example.FeedBack.model.FeedBackQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackQuestionRepository extends JpaRepository<FeedBackQuestion, Integer> {
}