package com.phishguard.backend.kafka;

import com.phishguard.backend.model.BehaviorEvent;
import com.phishguard.backend.repository.BehaviorEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> messages = new ArrayList<>();
    private final BehaviorEventRepository repository;

    public KafkaConsumerService(BehaviorEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = KafkaProducerService.TOPIC,
            groupId = "phishguard-group"
    )
    public void consume(String message) {

        // 1️⃣ Store raw message for stream API
        messages.add(message);

        // 2️⃣ TEMP: Create BehaviorEvent (JSON parsing comes later)
        BehaviorEvent event = new BehaviorEvent(
                "u123",
                "LinkedIn",
                "AI jobs",
                "Remote freelancing",
                LocalDateTime.now()
        );

        // 3️⃣ SAVE TO MONGODB
        repository.save(event);
    }

    public List<String> getMessages() {
        return messages;
    }
}
