package com.example.FeedBack.service; // Updated package

import com.example.FeedBack.model.Student; // Updated import
import com.example.FeedBack.repository.StudentRepository; // Updated import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;



    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByStuId(String stuId){
        return studentRepository.findByStuId(stuId);
    }



    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}