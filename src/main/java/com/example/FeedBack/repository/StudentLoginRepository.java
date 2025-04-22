package com.example.FeedBack.repository;

import com.example.FeedBack.model.StudentLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLoginRepository extends JpaRepository<StudentLogin, String> {
    StudentLogin findByStuId(String stuId);
}