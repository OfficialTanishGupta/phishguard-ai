package com.phishguard.backend.controller;

import com.phishguard.backend.ai.FakeInterestGenerator;
import com.phishguard.backend.kafka.KafkaConsumerService;
import com.phishguard.backend.kafka.KafkaProducerService;
import com.phishguard.backend.model.UserBehavior;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;
    private final FakeInterestGenerator fakeInterestGenerator;

    // ✅ SINGLE constructor (correct dependency injection)
    public HealthController(KafkaProducerService kafkaProducerService,
                            KafkaConsumerService kafkaConsumerService,
                            FakeInterestGenerator fakeInterestGenerator) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaConsumerService = kafkaConsumerService;
        this.fakeInterestGenerator = fakeInterestGenerator;
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

    @PostMapping("/behavior")
    public String receiveBehavior(@RequestBody UserBehavior behavior) {

        String fakeInterest =
                fakeInterestGenerator.generateFakeInterest(behavior.getRealInterest());

        String kafkaMessage =
                "User=" + behavior.getUserId() +
                        ", Platform=" + behavior.getPlatform() +
                        ", RealInterest=" + behavior.getRealInterest() +
                        ", FakeInterest=" + fakeInterest;

        kafkaProducerService.sendMessage(kafkaMessage);

        return "Behavior processed with fake interest generation";
    }
}
