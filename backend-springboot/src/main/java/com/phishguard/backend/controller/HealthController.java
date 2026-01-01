package com.phishguard.backend.controller;

import java.util.List;


import com.phishguard.backend.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.phishguard.backend.kafka.KafkaConsumerService;


@RestController
public class HealthController {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;


    public HealthController(KafkaProducerService kafkaProducerService,
                            KafkaConsumerService kafkaConsumerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @GetMapping("/health")
    public String healthCheck() {

        kafkaProducerService.sendMessage("Health API was called");

        return "PhishGuard AI Backend is running!";
    }

    @GetMapping("/kafka/stream")
    public List<String> getKafkaStream() {
        return kafkaConsumerService.getMessages();
    }
}
