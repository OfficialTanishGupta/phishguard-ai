package com.phishguard.backend.controller;

import com.phishguard.backend.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final KafkaProducerService kafkaProducerService;

    public HealthController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/health")
    public String healthCheck() {

        kafkaProducerService.sendMessage("Health API was called");

        return "PhishGuard AI Backend is running!";
    }
}
