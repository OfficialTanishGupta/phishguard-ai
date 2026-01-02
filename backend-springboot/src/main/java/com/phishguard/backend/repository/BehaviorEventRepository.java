package com.phishguard.backend.repository;

import com.phishguard.backend.model.BehaviorEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BehaviorEventRepository
        extends MongoRepository<BehaviorEvent, String> {
}
