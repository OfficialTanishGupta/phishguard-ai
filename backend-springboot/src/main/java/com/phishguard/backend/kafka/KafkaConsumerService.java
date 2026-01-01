package com.phishguard.backend.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "user.real.behavior", groupId = "phishguard-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
