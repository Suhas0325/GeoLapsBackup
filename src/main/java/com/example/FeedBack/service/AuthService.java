package com.example.FeedBack.service;

import com.example.FeedBack.model.StudentLogin;
import com.example.FeedBack.repository.StudentLoginRepository;
import com.example.FeedBack.repository.StudentRepository;
import com.example.FeedBack.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String authenticate(String stuId , String password){
        StudentLogin studentLogin = studentLoginRepository.findByStuId(stuId);
        System.out.println("Hello 2");
        System.out.println(studentLogin.getPassword());
        if(studentLogin != null && passwordEncoder.matches(password , studentLogin.getPassword()) ){

            System.out.println("Hello 3");
            return jwtUtil.generateToken(stuId);
        }
        System.out.println("Hello 4");

        return null;
    }

    public boolean changePassword(String stuId, String oldPassword, String newPassword) {
        StudentLogin studentLogin = studentLoginRepository.findByStuId(stuId);
        if (studentLogin != null && passwordEncoder.matches(oldPassword, studentLogin.getPassword())) {
            // Encrypt the new password and update it
            studentLogin.setPassword(passwordEncoder.encode(newPassword));
            studentLoginRepository.save(studentLogin);
            return true;
        }
        return false;
    }
}