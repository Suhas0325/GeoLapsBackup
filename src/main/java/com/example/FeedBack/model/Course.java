package com.example.FeedBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // Add this import
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "coursess")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "credits")
    private Integer credits;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore // Add this annotation to break circular reference
    private List<FacMapping> facMappings;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CourseDept> courseDepts;

    public List<CourseDept> getCourseDepts() {
        return courseDepts;
    }

    public void setCourseDepts(List<CourseDept> courseDepts) {
        this.courseDepts = courseDepts;
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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<FacMapping> getFacMappings() {
        return facMappings;
    }

    public void setFacMappings(List<FacMapping> facMappings) {
        this.facMappings = facMappings;
    }
}