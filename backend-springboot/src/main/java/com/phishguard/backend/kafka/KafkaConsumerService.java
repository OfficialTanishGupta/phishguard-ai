package com.phishguard.backend.kafka;

import com.phishguard.backend.model.SyntheticBehaviorEvent;
import com.phishguard.backend.service.BehaviorEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }

    private final BehaviorEventService service;

    public KafkaConsumerService(BehaviorEventService service) {
        this.service = service;
    }

    @KafkaListener(topics = "phishguard-user-behavior")
    public void consume(String message) {
        messages.add(message);
    }
}
