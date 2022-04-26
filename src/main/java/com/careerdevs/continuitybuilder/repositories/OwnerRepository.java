package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUser_id(Long id);
}
