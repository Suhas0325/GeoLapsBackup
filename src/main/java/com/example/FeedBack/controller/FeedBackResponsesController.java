package com.example.FeedBack.controller;

import com.example.FeedBack.model.FeedBackResponses;
import com.example.FeedBack.service.FeedBackResponseService;
import com.example.FeedBack.dto.FeedbackSubmissionDTO;
import com.example.FeedBack.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback/responses")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174"})
public class FeedBackResponsesController {

    private final FeedBackResponseService feedBackResponseService;
    private final JwtUtil jwtUtil;

    @Autowired
    public FeedBackResponsesController(FeedBackResponseService feedBackResponseService, JwtUtil jwtUtil) {
        this.feedBackResponseService = feedBackResponseService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/save")
    public ResponseEntity<?> submitResponses(
            @Valid @RequestBody List<FeedbackSubmissionDTO> feedbackDtos,
            BindingResult bindingResult,
            @RequestHeader("Authorization") String authHeader) {
         System.out.println(feedbackDtos);
         System.out.println("hello pook");
        // Validate request body
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Validation failed",
                    "errors", errors
            ));
        }

        try {
            // Extract and validate JWT token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "error",
                        "message", "Invalid authorization header"
                ));
            }

            String token = authHeader.substring(7);
            String studentId = jwtUtil.extractUsername(token);

            // Process feedback submissions
            List<FeedBackResponses> savedResponses = feedBackResponseService.saveResponses(feedbackDtos, studentId);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Feedback submitted successfully"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "error",
                    "message", "Internal server error",
                    "detail", e.getMessage()
            ));
        }
    }
}