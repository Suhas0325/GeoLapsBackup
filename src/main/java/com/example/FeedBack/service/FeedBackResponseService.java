package com.example.FeedBack.service;

import com.example.FeedBack.dto.FeedbackSubmissionDTO;
import com.example.FeedBack.model.*;
import com.example.FeedBack.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackResponseService {

    private final FeedBackResponseRepository feedBackResponsesRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final FeedBackQuestionRepository questionRepository;

    @Autowired
    public FeedBackResponseService(
            FeedBackResponseRepository feedBackResponsesRepository,
            StudentRepository studentRepository,
            FacultyRepository facultyRepository,
            CourseRepository courseRepository,
            FeedBackQuestionRepository questionRepository) {
        this.feedBackResponsesRepository = feedBackResponsesRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public List<FeedBackResponses> saveResponses(List<FeedbackSubmissionDTO> feedbackDtos, String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return feedbackDtos.stream()
                .map(dto -> {
                    FeedBackQuestion question = questionRepository.findById(dto.getQuestionId())
                            .orElseThrow(() -> new IllegalArgumentException("Question not found"));

                    // Validate response type matches question type
                    if ("rating".equals(question.getQuestionType())) {
                        if (dto.getResponseValue() == null) {
                            throw new IllegalArgumentException("Rating value required for question ID: " + question.getQuestionId());
                        }
                        dto.setResponseText(null); // Ensure text is null for ratings
                    } else {
                        if (dto.getResponseText() == null || dto.getResponseText().trim().isEmpty()) {
                            throw new IllegalArgumentException("Text response required for question ID: " + question.getQuestionId());
                        }
                        dto.setResponseValue(null); // Ensure rating is null for text
                    }

                    FeedBackResponses response = new FeedBackResponses();
                    response.setStudent(student);
                    response.setFaculty(facultyRepository.findById(dto.getFacultyId()).orElseThrow());
                    response.setCourse(courseRepository.findById(dto.getCourseId()).orElseThrow());
                    response.setQuestion(question);
                    response.setResponseValue(dto.getResponseValue());
                    response.setResponseText(dto.getResponseText());
                    response.setTimeTaken(dto.getTimeTaken());

                    return feedBackResponsesRepository.save(response);
                })
                .collect(Collectors.toList());
    }
}