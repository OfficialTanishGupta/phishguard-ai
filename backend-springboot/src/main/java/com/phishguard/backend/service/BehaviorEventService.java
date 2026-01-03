package com.phishguard.backend.service;

import com.phishguard.backend.model.SyntheticBehaviorEvent;
import com.phishguard.backend.repository.SyntheticBehaviorRepository;
import org.springframework.stereotype.Service;

@Service
public class BehaviorEventService {

    private final SyntheticBehaviorRepository repository;

    public BehaviorEventService(SyntheticBehaviorRepository repository) {
        this.repository = repository;
    }

    public void saveSyntheticEvent(SyntheticBehaviorEvent event) {
        repository.save(event);
    }
}
