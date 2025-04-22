package com.example.FeedBack.dto;

public class FacultyDTO {
    private String facultyId;
    private String facultyName;
    private String phoneNumber;
    private String dateOfEnrollment;
    private Double salary;

    // Constructor, Getters, and Setters
    public FacultyDTO(String facultyId, String facultyName, String phoneNumber, String dateOfEnrollment, Double salary) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.phoneNumber = phoneNumber;
        this.dateOfEnrollment = dateOfEnrollment;
        this.salary = salary;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}