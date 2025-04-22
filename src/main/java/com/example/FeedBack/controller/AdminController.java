package com.example.FeedBack.controller;

import com.example.FeedBack.dto.AdminLoginRequest;
import com.example.FeedBack.model.Course;
import com.example.FeedBack.model.FacMapping;
import com.example.FeedBack.model.Faculty;
import com.example.FeedBack.model.Student;
import com.example.FeedBack.model.FeedBackResponses;
import com.example.FeedBack.repository.FeedBackResponseRepository;
import com.example.FeedBack.repository.StudentRepository;
import com.example.FeedBack.service.AdminService;
import com.example.FeedBack.util.JwtUtil;
import com.example.FeedBack.repository.FacMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;



    @Autowired
    private FacMappingRepository facMappingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FeedBackResponseRepository feedBackResponseRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest request) {
        String token = adminService.authenticate(request.getLoginId(), request.getPassword());
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body("Authentication failed");
    }

    @GetMapping("/getFeedback")
    public ResponseEntity<?> getFeedback() {
        List<FacMapping> mappings = facMappingRepository.findAll();

        List<Map<String, Object>> response = mappings.stream().map(mapping -> {
            Faculty faculty = mapping.getFaculty();
            Course course = mapping.getCourse();

            // Fetch feedback responses for this course & faculty
            List<FeedBackResponses> feedbacks = feedBackResponseRepository
                    .findByCourseIdAndFacultyId(course.getCourseId(), faculty.getFacultyId());

            // Collect unique student IDs from feedbacks
            Set<String> studentIds = feedbacks.stream()
                    .map(feedback -> feedback.getStudent().getStuId()) // Assuming stuId is String
                    .collect(Collectors.toSet());

            // Fetch student details
            List<Student> students = studentRepository.findByStuIdIn(studentIds);

            // Create a map for student ID -> Student details lookup
            Map<String, Student> studentMap = students.stream()
                    .collect(Collectors.toMap(Student::getStuId, student -> student));

            // Format the response
            Map<String, Map<String, Object>> studentFeedbackMap = new HashMap<>();

            feedbacks.forEach(feedback -> {
                String studentId = feedback.getStudent().getStuId();
                Student student = studentMap.get(studentId);

                // If student entry does not exist, create it
                studentFeedbackMap.putIfAbsent(studentId, new HashMap<>());
                Map<String, Object> studentData = studentFeedbackMap.get(studentId);

                // Set basic student info
                studentData.put("facultyName", faculty.getFacultyName());
                studentData.put("courseName", course.getCourseName());
                studentData.put("studentId", studentId);
                studentData.put("studentName", student.getStuName());
                studentData.put("timeTaken", feedback.getTimeTaken());
                studentData.put("cgpa", student.getGpa());  // Adding CGPA
                studentData.put("attendance", student.getAttendance());  // Adding Attendance

                // Prepare feedback structure
                Map<String, Map<String, Object>> feedbackMap =
                        (Map<String, Map<String, Object>>) studentData.getOrDefault("feedback", new HashMap<>());

                Map<String, Object> questionData = new HashMap<>();
                questionData.put("question", feedback.getQuestion().getQuestionText());
                questionData.put("answer", feedback.getResponseText() != null ? feedback.getResponseText() : feedback.getResponseValue());

                feedbackMap.put(String.valueOf(feedback.getQuestion().getQuestionId()), questionData);
                studentData.put("feedback", feedbackMap);
            });

            return new ArrayList<>(studentFeedbackMap.values());
        }).flatMap(Collection::stream).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }



}
