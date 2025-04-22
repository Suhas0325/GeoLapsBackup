package com.example.FeedBack.repository;

import com.example.FeedBack.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Integer> {

    List<StudentEnrollment> findByStudent_StuId(String stuId);

    Optional<StudentEnrollment> findByStudent_StuIdAndCourse_CourseId(
            String stuId, Integer courseId);

}
