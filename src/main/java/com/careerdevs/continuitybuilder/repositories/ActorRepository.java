package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
