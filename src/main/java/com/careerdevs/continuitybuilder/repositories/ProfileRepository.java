package com.careerdevs.continuitybuilder.repositories;

import com.careerdevs.continuitybuilder.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
