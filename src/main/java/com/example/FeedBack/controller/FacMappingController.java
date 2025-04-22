package com.example.FeedBack.controller;

import com.example.FeedBack.dto.FacMappingDTO;
import com.example.FeedBack.service.FacMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fac-mappings")
public class FacMappingController {

    @Autowired
    private FacMappingService facMappingService;

    @GetMapping
    public List<FacMappingDTO> getAllFacMappings() {
        return facMappingService.getAllFacMappings();
    }
}