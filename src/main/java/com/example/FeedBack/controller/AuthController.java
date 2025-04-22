package com.example.FeedBack.controller;

import com.example.FeedBack.dto.ChangePasswordRequest;
import com.example.FeedBack.dto.LoginRequest;
import com.example.FeedBack.service.AuthService;
import com.example.FeedBack.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getStuId(), loginRequest.getPassword());
        if (token != null) {
            return token; // Return JWT token
        }
        return "Authentication failed"; // Return error message
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, HttpServletRequest httpRequest) {
        // Extract token from header
        String token = httpRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid token");
        }
        token = token.substring(7); // Remove "Bearer " prefix

        // Extract stuId from token
        String stuId = jwtUtil.extractUsername(token);

        // Change password
        boolean isChanged = authService.changePassword(stuId, request.getOldPassword(), request.getNewPassword());
        if (isChanged) {
            return ResponseEntity.ok("Password changed successfully!");
        }
        return ResponseEntity.badRequest().body("Old password is incorrect!");
    }
}