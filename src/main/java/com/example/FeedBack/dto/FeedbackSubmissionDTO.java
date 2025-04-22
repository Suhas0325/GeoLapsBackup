package com.example.FeedBack.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

public class FeedbackSubmissionDTO {
    @NotBlank(message = "Faculty ID is required")
    private String facultyId;

    @NotNull(message = "Course ID is required")
    private Integer courseId;

    @NotNull(message = "Question ID is required")
    private Integer questionId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private Integer responseValue;  // Only for rating questions (1-10)

    @Size(min = 1, max = 500, message = "Text response must be between 1-500 characters")
    private String responseText;   // Only for text questions


    private Long timeTaken;

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    // Getters and Setters
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(Integer responseValue) {
        this.responseValue = responseValue;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    // Validation method
    public void validateQuestionType(String expectedType) {
        if ("rating".equals(expectedType)) {
            if (this.responseValue == null) {
                throw new IllegalArgumentException("Rating value is required for rating questions");
            }
            if (this.responseText != null) {
                throw new IllegalArgumentException("Text response should be null for rating questions");
            }
        } else if ("text".equals(expectedType)) {
            if (this.responseText == null || this.responseText.trim().isEmpty()) {
                throw new IllegalArgumentException("Text response is required for text questions");
            }
            if (this.responseValue != null) {
                throw new IllegalArgumentException("Rating value should be null for text questions");
            }
        }
    }
}