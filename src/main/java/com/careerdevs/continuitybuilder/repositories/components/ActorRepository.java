package com.careerdevs.continuitybuilder.repositories.components;

import com.careerdevs.continuitybuilder.models.components.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
