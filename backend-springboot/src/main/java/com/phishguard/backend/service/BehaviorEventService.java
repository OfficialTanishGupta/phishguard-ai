package com.phishguard.backend.service;

import com.phishguard.backend.model.BehaviorEvent;
import com.phishguard.backend.repository.BehaviorEventRepository;
import org.springframework.stereotype.Service;

@Service
public class BehaviorEventService {

    private final BehaviorEventRepository repository;

    public BehaviorEventService(BehaviorEventRepository repository) {
        this.repository = repository;
    }

    public void saveEvent(BehaviorEvent event) {
        repository.save(event);
    }
}
