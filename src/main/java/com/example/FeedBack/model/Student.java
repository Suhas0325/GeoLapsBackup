package com.example.FeedBack.model; // Updated package

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "stu_id" , length = 20)
    private String stuId;

    @Column(name = "stu_name", nullable = false)
    private String stuName;

    @Column(name = "stu_address")
    private String stuAddress;

    @Column(name = "stu_mobile")
    private String stuMobile;

    @Column(name = "stu_email")
    private String stuEmail;

    @Column(name = "Enrollment_date")
    private Date enrollmentDate;



    @Column(name = "GPA")
    private Float gpa;

    @Column(name = "Attendance")
    private Float attendance;

    @Column(name = "section_name" , length = 50)
    private String section;


//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    @JsonIgnore // This prevents the attendances from being serialized
//    private List<Attendance> attendances;

    // Getters and Setters
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuMobile() {
        return stuMobile;
    }

    public void setStuMobile(String stuMobile) {
        this.stuMobile = stuMobile;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Float getAttendance() {
        return attendance;
    }

    public void setAttendance(Float attendance) {
        this.attendance = attendance;
    }

//    public List<Attendance> getAttendances() {
//        return attendances;
//    }
//
//    public void setAttendances(List<Attendance> attendances) {
//        this.attendances = attendances;
//    }
}