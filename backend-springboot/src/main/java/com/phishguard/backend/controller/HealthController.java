package com.phishguard.backend.controller;

import com.phishguard.backend.ai.FakeInterestGenerator;
import com.phishguard.backend.kafka.KafkaConsumerService;
import com.phishguard.backend.kafka.KafkaProducerService;
import com.phishguard.backend.model.UserBehavior;
import com.phishguard.backend.model.SyntheticBehaviorEvent;
import com.phishguard.backend.service.BehaviorEventService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class HealthController {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;
    private final FakeInterestGenerator fakeInterestGenerator;
    private final BehaviorEventService behaviorEventService;

    public HealthController(KafkaProducerService kafkaProducerService,
                            KafkaConsumerService kafkaConsumerService,
                            FakeInterestGenerator fakeInterestGenerator,
                            BehaviorEventService behaviorEventService) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaConsumerService = kafkaConsumerService;
        this.fakeInterestGenerator = fakeInterestGenerator;
        this.behaviorEventService = behaviorEventService;
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

    // ✅ SINGLE /behavior METHOD
    @PostMapping("/behavior")
    public String receiveBehavior(@RequestBody UserBehavior behavior) {

        // Generate fake interest (AI v2)
        String fakeInterest =
                fakeInterestGenerator.generateFakeInterest(behavior.getRealInterest());

        // Create synthetic event (FAKE ONLY)
        SyntheticBehaviorEvent syntheticEvent =
                new SyntheticBehaviorEvent(
                        behavior.getUserId(),
                        fakeInterest,
                        LocalDateTime.now()
                );

        // Save ONLY fake behavior
        behaviorEventService.saveSyntheticEvent(syntheticEvent);

        // Send ONLY fake behavior to Kafka
        kafkaProducerService.sendMessage(syntheticEvent.toString());

        return "Synthetic behavior generated and streamed";
    }
}
