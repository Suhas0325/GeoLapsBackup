package com.example.FeedBack.service;

import com.example.FeedBack.model.StudentEnrollment;
import com.example.FeedBack.repository.StudentEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentEnrollmentService {

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    public List<StudentEnrollment> getAllEnrollments() {
        return studentEnrollmentRepository.findAll();
    }

    public Optional<StudentEnrollment> getEnrollmentById(Integer id) {
        return studentEnrollmentRepository.findById(id);
    }

    public List<StudentEnrollment> getEnrollmentsByStudentId(String studentId) {
        return studentEnrollmentRepository.findByStudent_StuId(studentId);
    }

    public StudentEnrollment saveEnrollment(StudentEnrollment enrollment) {
        return studentEnrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        studentEnrollmentRepository.deleteById(id);
    }
}