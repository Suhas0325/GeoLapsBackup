package com.example.FeedBack.repository;

import com.example.FeedBack.model.CourseDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDeptRepository extends JpaRepository<CourseDept, Integer> {
}