//package com.example.FeedBack.service;
//
//import com.example.FeedBack.model.Attendance;
//import com.example.FeedBack.model.ClassRoom;
//import com.example.FeedBack.model.Student;
//import com.example.FeedBack.repository.AttendanceRepository;
//import com.example.FeedBack.repository.ClassRoomRepository;
//import com.example.FeedBack.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.List;
//
//@Service
//public class AttendanceService {
//
//    private final AttendanceRepository attendanceRepository;
//    private final ClassRoomRepository classRoomRepository;
//    private final StudentRepository studentRepository;
//    private final LocationVerificationService locationVerificationService;
//
//    @Autowired
//    public AttendanceService(AttendanceRepository attendanceRepository,
//                             ClassRoomRepository classRoomRepository,
//                             StudentRepository studentRepository,
//                             LocationVerificationService locationVerificationService) {
//        this.attendanceRepository = attendanceRepository;
//        this.classRoomRepository = classRoomRepository;
//        this.studentRepository = studentRepository;
//        this.locationVerificationService = locationVerificationService;
//    }
//
//    @Transactional
//    public Attendance markAttendance(String studentId, Integer classRoomId,
//                                     Double latitude, Double longitude) throws Exception {
//
//        // 1. Validate student exists
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new Exception("Student not found with ID: " + studentId));
//
//        // 2. Validate classroom exists
//        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
//                .orElseThrow(() -> new Exception("Classroom not found with ID: " + classRoomId));
//
//        // 3. Determine current period based on time
//        String currentPeriod = determineCurrentPeriod();
//
//        // 4. Check if attendance already marked for this period
//        if (isAttendanceAlreadyMarked(studentId, currentPeriod)) {
//            throw new Exception("Attendance already marked for " + currentPeriod);
//        }
//
//        // 5. Verify location
//        if (!locationVerificationService.isWithinRadius(
//                latitude, longitude,
//                classRoom.getLatitude(), classRoom.getLongitude(),
//                classRoom.getRadius())) {
//            throw new Exception("You are not within the classroom radius");
//        }
//
//        // 6. Create and save attendance record
//        Attendance attendance = new Attendance();
//        attendance.setStudent(student);
//        attendance.setTimestamp(LocalDateTime.now());
//        attendance.setPeriod(currentPeriod);
//        attendance.setLatitude(latitude);
//        attendance.setLongitude(longitude);
//        attendance.setVerified(true);
//
//        return attendanceRepository.save(attendance);
//    }
//
//    private String determineCurrentPeriod() {
//        LocalTime now = LocalTime.now();
//
//        // Adjust these times according to your schedule
//        if (now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(9, 30))) {
//            return "1st Period";
//        } else if (now.isAfter(LocalTime.of(9, 30)) && now.isBefore(LocalTime.of(11, 0))) {
//            return "2nd Period";
//        } else if (now.isAfter(LocalTime.of(11, 0)) && now.isBefore(LocalTime.of(12, 30))) {
//            return "3rd Period";
//        } else {
//            return "Other Period";
//        }
//    }
//
//    private boolean isAttendanceAlreadyMarked(String studentId, String period) {
//        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
//        LocalDateTime todayEnd = todayStart.plusDays(1);
//
//        return attendanceRepository.existsByStudentStuIdAndPeriodAndTimestampBetween(
//                studentId, period, todayStart, todayEnd);
//    }
//
////    public List<Attendance> getStudentAttendance(String studentId) {
////        return attendanceRepository.findByStudentStuId(studentId);
////    }
//}