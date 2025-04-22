package com.example.FeedBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // Add this import
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "section_name", length = 50)
    private String sectionName;


    @OneToMany(mappedBy = "section" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<FacMapping> facMappings;

    // Getters and Setters
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<FacMapping> getFacMappings() {
        return facMappings;
    }

    public void setFacMappings(List<FacMapping> facMappings) {
        this.facMappings = facMappings;
    }
}