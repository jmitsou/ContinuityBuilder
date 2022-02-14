package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
