package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
