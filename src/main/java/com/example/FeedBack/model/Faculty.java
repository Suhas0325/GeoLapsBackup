package com.example.FeedBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @Column(name = "faculty_id", length = 10)
    private String facultyId;

    @Column(name = "faculty_name", nullable = false, length = 100)
    private String facultyName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "date_of_enrollment")
    @Temporal(TemporalType.DATE)
    private Date dateOfEnrollment;

    @Column(name = "salary") // Works only with BigDecimal
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", nullable = false)
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<FacMapping> facMappings;

    // Getters and Setters
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

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<FacMapping> getFacMappings() {
        return facMappings;
    }

    public void setFacMappings(List<FacMapping> facMappings) {
        this.facMappings = facMappings;
    }
}
