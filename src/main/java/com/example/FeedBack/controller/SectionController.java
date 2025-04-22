package com.example.FeedBack.controller;

import com.example.FeedBack.dto.SectionDTO;
import com.example.FeedBack.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<SectionDTO> getAllSections() {
        return sectionService.getAllSections();
    }
}