package com.persnalportfolio.personalportfolio.repositories;

import com.persnalportfolio.personalportfolio.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
