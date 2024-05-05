package com.persnalportfolio.personalportfolio.repositories;

import com.persnalportfolio.personalportfolio.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminId(String adminId);
}
