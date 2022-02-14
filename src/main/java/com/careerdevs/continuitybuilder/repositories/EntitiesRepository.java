package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Entities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntitiesRepository extends JpaRepository<Entities, Long> {
}
