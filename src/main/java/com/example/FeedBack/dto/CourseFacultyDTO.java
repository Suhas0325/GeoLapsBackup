package com.example.FeedBack.dto;

public class CourseFacultyDTO {
    private Integer courseId;
    private String courseName;
    private String facultyId;
    private String facultyName;
    private boolean hasSubmitted;

    public CourseFacultyDTO(Integer courseId, String courseName, String facultyId, String facultyName,boolean hasSubmitted) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.hasSubmitted = hasSubmitted;
    }

    public boolean isHasSubmitted() {
        return hasSubmitted;
    }

    public void setHasSubmitted(boolean hasSubmitted) {
        this.hasSubmitted = hasSubmitted;
    }

    // Getters and Setters
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
}
