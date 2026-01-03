package com.phishguard.backend.repository;

import com.phishguard.backend.model.SyntheticBehaviorEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyntheticBehaviorRepository
        extends JpaRepository<SyntheticBehaviorEvent, Long> {
}
