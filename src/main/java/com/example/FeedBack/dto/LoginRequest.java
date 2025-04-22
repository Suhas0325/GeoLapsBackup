package com.example.FeedBack.dto;

public class LoginRequest {
    private String stuId;
    private String password;

    // Getters and Setters
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}