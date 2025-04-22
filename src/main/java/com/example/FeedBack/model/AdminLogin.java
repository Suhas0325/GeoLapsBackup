package com.example.FeedBack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_login")
public class AdminLogin {

    @Id
    @Column(name = "login_id", length = 20)
    private String loginId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // Getters and Setters
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
