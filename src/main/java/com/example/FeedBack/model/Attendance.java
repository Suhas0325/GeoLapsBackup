//package com.example.FeedBack.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "attendance")
//public class Attendance {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
//    private Student student;
//
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
//    @Column(nullable = false)
//    private String period;
//
//    @Column(nullable = false)
//    private Double latitude;
//
//    @Column(nullable = false)
//    private Double longitude;
//
//    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean verified;
//
//    @Column(name = "created_at", insertable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    // Getters and Setters
//
//
//    public Attendance() {}
//
//    public Attendance(Student student, LocalDateTime timestamp, String period,
//                      Double latitude, Double longitude) {
//        this.student = student;
//        this.timestamp = timestamp;
//        this.period = period;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public boolean isVerified() {
//        return verified;
//    }
//
//    public void setVerified(boolean verified) {
//        this.verified = verified;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//}