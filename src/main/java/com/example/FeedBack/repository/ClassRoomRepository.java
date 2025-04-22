package com.example.FeedBack.repository;

import com.example.FeedBack.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {


    Optional<ClassRoom> findByName(String name);

    // Find classrooms containing name (case-insensitive)
    List<ClassRoom> findByNameContainingIgnoreCase(String name);

    // Find classrooms in a specific building
    List<ClassRoom> findByBuilding(String building);

    // Custom query to find classrooms within a geographic area
    @Query("SELECT c FROM ClassRoom c WHERE " +
            "FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', c.longitude, c.latitude), " +
            "FUNCTION('POINT', :longitude, :latitude)) <= :radius")
    List<ClassRoom> findNearbyClassrooms(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius);

    // Find classrooms with available radius greater than specified
    List<ClassRoom> findByRadiusGreaterThan(double minRadius);

    // Find classrooms with available radius less than specified
    List<ClassRoom> findByRadiusLessThan(double maxRadius);
}