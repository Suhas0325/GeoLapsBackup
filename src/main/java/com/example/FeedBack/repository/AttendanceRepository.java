//package com.example.FeedBack.repository;
//
//import com.example.FeedBack.model.Attendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
//
//    Boolean existsByStudentStuIdAndPeriodAndTimestampBetween(
//            String studentId,
//            String period,
//            LocalDateTime start,
//            LocalDateTime end
//    );
//}