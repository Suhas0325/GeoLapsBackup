package com.example.FeedBack.repository;

import com.example.FeedBack.model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<AdminLogin, String> {
    AdminLogin findByLoginId(String loginId);
}
