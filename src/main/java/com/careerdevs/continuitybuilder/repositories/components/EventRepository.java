package com.careerdevs.continuitybuilder.repositories.components;

import com.careerdevs.continuitybuilder.models.components.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
