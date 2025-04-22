package com.example.FeedBack.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departmentss")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @OneToMany(mappedBy = "department" , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Faculty> facultyList;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<CourseDept> courseDepts;  // Added for Course_Dept relationship


    public List<CourseDept> getCourseDepts() {
        return courseDepts;
    }

    public void setCourseDepts(List<CourseDept> courseDepts) {
        this.courseDepts = courseDepts;
    }

    // Getters and Setters
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

}