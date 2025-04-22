package com.example.FeedBack.repository;

import com.example.FeedBack.model.FacMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacMappingRepository extends JpaRepository<FacMapping, Integer> {
}