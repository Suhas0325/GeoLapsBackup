package com.example.FeedBack.service;

import com.example.FeedBack.model.AdminLogin;
import com.example.FeedBack.repository.AdminLoginRepository;
import com.example.FeedBack.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticate(String loginId, String password) {
        AdminLogin admin = adminLoginRepository.findByLoginId(loginId);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return jwtUtil.generateToken(loginId); // Return JWT token
        }
        return null;
    }


}
