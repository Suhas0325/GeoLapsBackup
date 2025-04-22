package com.example.FeedBack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_login")
public class StudentLogin {

    @Id
    @Column(name = "stu_id", length = 20)
    private String stuId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name = "stu_id")
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}