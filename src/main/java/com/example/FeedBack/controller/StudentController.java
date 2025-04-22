package com.example.FeedBack.controller; // Updated package

import com.example.FeedBack.dto.CourseFacultyDTO;
import com.example.FeedBack.model.Student; // Updated import
import com.example.FeedBack.model.StudentEnrollment;
import com.example.FeedBack.service.StudentEnrollmentService;
import com.example.FeedBack.service.StudentService; // Updated import
import com.example.FeedBack.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174"})

@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentEnrollmentService enrollmentService;


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/me")
    public  Student getStudentDetails(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        String stuId = jwtUtil.extractUsername(jwtToken);

        return  studentService.getStudentByStuId(stuId);
    }


    @GetMapping("/me/courses")
    public List<CourseFacultyDTO> getEnrolledCoursesWithFaculty(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String stuId = jwtUtil.extractUsername(jwtToken);

        // Retrieve enrollments for the student

        List<StudentEnrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(stuId);

        // Prepare the response
        List<CourseFacultyDTO> response = new ArrayList<>();
        for (StudentEnrollment enrollment : enrollments) {
            String courseName = enrollment.getCourse().getCourseName();
            Integer courseId = enrollment.getCourse().getCourseId();
            String facultyName = enrollment.getCourse().getFacMappings().stream()
                    .findFirst()
                    .map(facMapping -> facMapping.getFaculty().getFacultyName())
                    .orElse("No Faculty Assigned");
            String facultyId = enrollment.getCourse().getFacMappings().stream()
                             .findFirst()
                             .map(facMapping -> facMapping.getFaculty().getFacultyId())
                             .orElse(null);
            boolean hasSubmitted = enrollment.isHasSubmitted();


            response.add(new CourseFacultyDTO(courseId,courseName,facultyId, facultyName,hasSubmitted));
        }
        return response;
    }




    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}