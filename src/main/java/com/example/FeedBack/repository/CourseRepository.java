package com.example.FeedBack.repository;

import com.example.FeedBack.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}