package com.example.FeedBack.controller;

import com.example.FeedBack.model.FeedBackQuestion;
import com.example.FeedBack.service.FeedBackQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174"})
@RequestMapping("/api/feedback/questions")
public class FeedBackQuestionController {

    @Autowired
    private FeedBackQuestionService feedBackQuestionService;

    // Get all feedback questions
    @GetMapping
    public ResponseEntity<List<FeedBackQuestion>> getAllQuestions() {
        List<FeedBackQuestion> questions = feedBackQuestionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Create a new feedback question
    @PostMapping
    public ResponseEntity<FeedBackQuestion> createQuestion(@RequestBody FeedBackQuestion question) {
        FeedBackQuestion savedQuestion = feedBackQuestionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    // Delete a feedback question by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        feedBackQuestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}