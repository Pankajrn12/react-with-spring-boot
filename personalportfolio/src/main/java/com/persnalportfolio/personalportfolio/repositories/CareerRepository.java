package com.persnalportfolio.personalportfolio.repositories;

import com.persnalportfolio.personalportfolio.entities.CareerForms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<CareerForms, Long> {
}
