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
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    @Autowired
    public FeedBackResponseService(
            FeedBackResponseRepository feedBackResponsesRepository,
            StudentRepository studentRepository,
            FacultyRepository facultyRepository,
            CourseRepository courseRepository,
            FeedBackQuestionRepository questionRepository,
            StudentEnrollmentRepository studentEnrollmentRepository) {
        this.feedBackResponsesRepository = feedBackResponsesRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.questionRepository = questionRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    @Transactional
    public List<FeedBackResponses> saveResponses(List<FeedbackSubmissionDTO> feedbackDtos, String studentId) {
        if (feedbackDtos == null || feedbackDtos.isEmpty()) {
            throw new IllegalArgumentException("No feedback data provided");
        }

        // Get the first feedback item to identify course
        FeedbackSubmissionDTO firstFeedback = feedbackDtos.get(0);
        Integer courseId = firstFeedback.getCourseId();

        // Verify student exists
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Save all feedback responses
        List<FeedBackResponses> responses = feedbackDtos.stream()
                .map(dto -> {
                    FeedBackQuestion question = questionRepository.findById(dto.getQuestionId())
                            .orElseThrow(() -> new IllegalArgumentException("Question not found"));

                    // Validate response type matches question type
                    if ("rating".equals(question.getQuestionType())) {
                        if (dto.getResponseValue() == null) {
                            throw new IllegalArgumentException("Rating value required for question ID: " + question.getQuestionId());
                        }
                        dto.setResponseText(null);
                    } else {
                        if (dto.getResponseText() == null || dto.getResponseText().trim().isEmpty()) {
                            throw new IllegalArgumentException("Text response required for question ID: " + question.getQuestionId());
                        }
                        dto.setResponseValue(null);
                    }

                    FeedBackResponses response = new FeedBackResponses();
                    response.setStudent(student);
                    response.setFaculty(facultyRepository.findById(dto.getFacultyId()).orElseThrow());
                    response.setCourse(courseRepository.findById(dto.getCourseId()).orElseThrow());
                    response.setQuestion(question);
                    response.setResponseValue(dto.getResponseValue());
                    response.setResponseText(dto.getResponseText());
                    response.setTimeTaken(dto.getTimeTaken());

                    return response;
                })
                .collect(Collectors.toList());

        List<FeedBackResponses> savedResponses = feedBackResponsesRepository.saveAll(responses);

        // Update the enrollment status (now only needs studentId and courseId)
        updateEnrollmentStatus(studentId, courseId);

        return savedResponses;
    }

    private void updateEnrollmentStatus(String studentId, Integer courseId) {
        studentEnrollmentRepository.findByStudent_StuIdAndCourse_CourseId(studentId, courseId)
                .ifPresent(enrollment -> {
                    enrollment.setHasSubmitted(true);
                    studentEnrollmentRepository.save(enrollment);
                });
    }
}