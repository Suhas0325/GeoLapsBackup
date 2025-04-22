package com.example.FeedBack.controller;

import com.example.FeedBack.model.StudentEnrollment;
import com.example.FeedBack.service.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class StudentEnrollmentController {

    @Autowired
    private StudentEnrollmentService enrollmentService;

    @GetMapping
    public List<StudentEnrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<StudentEnrollment> getEnrollmentById(@PathVariable Integer enrollmentId) {
        Optional<StudentEnrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        return enrollment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentEnrollment createEnrollment(@RequestBody StudentEnrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}