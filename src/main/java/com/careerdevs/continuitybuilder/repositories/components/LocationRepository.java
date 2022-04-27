package com.careerdevs.continuitybuilder.repositories.components;

import com.careerdevs.continuitybuilder.models.components.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
