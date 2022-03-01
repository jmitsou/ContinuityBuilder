package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
