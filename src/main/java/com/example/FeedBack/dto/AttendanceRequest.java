//package com.example.FeedBack.dto; // Or your appropriate package
//
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * DTO for marking attendance with location validation
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AttendanceRequest {
//
//    @NotBlank(message = "Student ID is required")
//    @Size(min = 3, max = 20, message = "Student ID must be between 3 and 20 characters")
//    private String studentId;
//
//    @NotNull(message = "Classroom ID is required")
//    @Positive(message = "Classroom ID must be positive")
//    private Integer classRoomId;
//
//    @NotNull(message = "Latitude is required")
//    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
//    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
//    private Double latitude;
//
//    @NotNull(message = "Longitude is required")
//    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
//    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
//    private Double longitude;
//
//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public Integer getClassRoomId() {
//        return classRoomId;
//    }
//
//    public void setClassRoomId(Integer classRoomId) {
//        this.classRoomId = classRoomId;
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
//}