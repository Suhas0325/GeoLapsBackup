package com.example.FeedBack.service;

import com.example.FeedBack.model.FeedBackQuestion;
import com.example.FeedBack.repository.FeedBackQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackQuestionService {

    @Autowired
    private FeedBackQuestionRepository feedBackQuestionRepository;

    // Get all feedback questions
    public List<FeedBackQuestion> getAllQuestions() {
        return feedBackQuestionRepository.findAll();
    }

    // Save a new feedback question
    public FeedBackQuestion saveQuestion(FeedBackQuestion question) {
        return feedBackQuestionRepository.save(question);
    }

    // Delete a feedback question by ID
    public void deleteQuestion(Integer id) {
        feedBackQuestionRepository.deleteById(id);
    }
}