package com.persnalportfolio.personalportfolio.repositories;

import com.persnalportfolio.personalportfolio.entities.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
