package com.example.FeedBack.dto;

import java.util.List;

public class DepartmentDTO {
    private Integer deptId;
    private String deptName;
    private List<FacultyDTO> facultyList;

    // Constructor, Getters, and Setters
    public DepartmentDTO(Integer deptId, String deptName, List<FacultyDTO> facultyList) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.facultyList = facultyList;
    }

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

    public List<FacultyDTO> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<FacultyDTO> facultyList) {
        this.facultyList = facultyList;
    }
}