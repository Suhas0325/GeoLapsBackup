package com.example.FeedBack.model;


import jakarta.persistence.*;

@Entity
@Table(name = "faculty_login")
public class FacultyLogin {



    @Id
    @Column(name = "faculty_id" , length = 20)
    private String FacultyId;

    @Column(name = "password",nullable = false , length = 255)
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;


    public String getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(String facultyId) {
        FacultyId = facultyId;
    }

}
