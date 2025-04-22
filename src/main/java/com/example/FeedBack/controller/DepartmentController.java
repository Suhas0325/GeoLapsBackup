package com.example.FeedBack.controller;

import com.example.FeedBack.dto.DepartmentDTO;
import com.example.FeedBack.dto.FacultyDTO;
import com.example.FeedBack.model.Department;
import com.example.FeedBack.model.Faculty;
import com.example.FeedBack.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOs = departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departmentDTOs);
    }

    private DepartmentDTO convertToDTO(Department department) {
        List<FacultyDTO> facultyDTOs = department.getFacultyList().stream()
                .map(this::convertToFacultyDTO)
                .collect(Collectors.toList());
        return new DepartmentDTO(department.getDeptId(), department.getDeptName(), facultyDTOs);
    }

    private FacultyDTO convertToFacultyDTO(Faculty faculty) {
        return new FacultyDTO(
                faculty.getFacultyId(),
                faculty.getFacultyName(),
                faculty.getPhoneNumber(),
                faculty.getDateOfEnrollment().toString(),
                faculty.getSalary()
        );
    }
}