package com.example.FeedBack.repository;

import com.example.FeedBack.model.FeedBackResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackResponseRepository extends JpaRepository<FeedBackResponses, Integer> {

    // 1. Query to find responses by student, course and faculty (with mixed ID types)
    @Query("SELECT r FROM FeedBackResponses r WHERE " +
            "r.student.stuId = :studentId AND " +
            "r.course.courseId = :courseId AND " +
            "r.faculty.facultyId = :facultyId")
    List<FeedBackResponses> findByStudentIdAndCourseIdAndFacultyId(
            @Param("studentId") String studentId,
            @Param("courseId") Integer courseId,
            @Param("facultyId") String facultyId);

    // 2. Existence check with same parameters
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM FeedBackResponses r WHERE " +
            "r.student.stuId = :studentId AND " +
            "r.course.courseId = :courseId AND " +
            "r.faculty.facultyId = :facultyId")
    boolean existsByStudentIdAndCourseIdAndFacultyId(
            @Param("studentId") String studentId,
            @Param("courseId") Integer courseId,
            @Param("facultyId") String facultyId);

    // 3. Find all responses by student ID (String)
    List<FeedBackResponses> findByStudent_StuId(String studentId);

    // 4. Find all responses by course ID (Integer)
    List<FeedBackResponses> findByCourse_CourseId(Integer courseId);

    // 5. Find all responses by faculty ID (String)
    List<FeedBackResponses> findByFaculty_FacultyId(String facultyId);

    // 6. Find responses by student and course (mixed types)
    @Query("SELECT r FROM FeedBackResponses r WHERE " +
            "r.student.stuId = :studentId AND " +
            "r.course.courseId = :courseId")
    List<FeedBackResponses> findByStudentIdAndCourseId(
            @Param("studentId") String studentId,
            @Param("courseId") Integer courseId);

    // 7. Count responses by question (Integer) and faculty (String)
    @Query("SELECT COUNT(r) FROM FeedBackResponses r WHERE " +
            "r.question.questionId = :questionId AND " +
            "r.faculty.facultyId = :facultyId")
    long countByQuestionIdAndFacultyId(
            @Param("questionId") Integer questionId,
            @Param("facultyId") String facultyId);

    @Query("SELECT f from FeedBackResponses f WHERE f.course.id = :courseId AND f.faculty.id = :facultyId")
    List<FeedBackResponses> findByCourseIdAndFacultyId(Integer courseId, String facultyId);
}