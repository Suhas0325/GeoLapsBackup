package com.example.FeedBack.dto;

public class FacMappingDTO {
    private Integer teachId;
    private FacultyDTO faculty;
    private CourseDTO course;

    // Constructor, Getters, and Setters
    public FacMappingDTO(Integer teachId, FacultyDTO faculty, CourseDTO course) {
        this.teachId = teachId;
        this.faculty = faculty;
        this.course = course;
    }

    public Integer getTeachId() {
        return teachId;
    }

    public void setTeachId(Integer teachId) {
        this.teachId = teachId;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}