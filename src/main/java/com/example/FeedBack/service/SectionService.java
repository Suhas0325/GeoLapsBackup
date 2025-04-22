package com.example.FeedBack.service;

import com.example.FeedBack.dto.SectionDTO;
import com.example.FeedBack.model.Section;
import com.example.FeedBack.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<SectionDTO> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(section -> new SectionDTO(section.getSectionId(), section.getSectionName()))
                .collect(Collectors.toList());
    }
}