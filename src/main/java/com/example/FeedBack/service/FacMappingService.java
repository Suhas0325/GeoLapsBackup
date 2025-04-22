package com.example.FeedBack.service;

import com.example.FeedBack.dto.FacMappingDTO;
import com.example.FeedBack.dto.FacultyDTO;
import com.example.FeedBack.dto.CourseDTO;
import com.example.FeedBack.model.FacMapping;
import com.example.FeedBack.repository.FacMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacMappingService {

    @Autowired
    private FacMappingRepository facMappingRepository;

    public List<FacMappingDTO> getAllFacMappings() {
        return facMappingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FacMappingDTO convertToDTO(FacMapping facMapping) {
        FacultyDTO facultyDTO = new FacultyDTO(
                facMapping.getFaculty().getFacultyId(),
                facMapping.getFaculty().getFacultyName(),
                facMapping.getFaculty().getPhoneNumber(),
                facMapping.getFaculty().getDateOfEnrollment().toString(),
                facMapping.getFaculty().getSalary()
        );

        CourseDTO courseDTO = new CourseDTO(
                facMapping.getCourse().getCourseId(),
                facMapping.getCourse().getCourseName(),
                facMapping.getCourse().getCredits()
        );

        return new FacMappingDTO(
                facMapping.getTeachId(),
                facultyDTO,
                courseDTO
        );
    }
}