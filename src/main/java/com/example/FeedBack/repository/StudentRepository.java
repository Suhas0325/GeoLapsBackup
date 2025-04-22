package com.example.FeedBack.repository;


import com.example.FeedBack.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface StudentRepository extends JpaRepository<Student, String> {
   Student findByStuId(String stuId);

   List<Student> findByStuIdIn(Set<String> stuIds);

}