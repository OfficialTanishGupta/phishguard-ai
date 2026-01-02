package com.phishguard.backend.controller;

import com.phishguard.backend.ai.FakeInterestGenerator;
import com.phishguard.backend.kafka.KafkaConsumerService;
import com.phishguard.backend.kafka.KafkaProducerService;
import com.phishguard.backend.model.UserBehavior;
import com.phishguard.backend.model.BehaviorEvent;
import com.phishguard.backend.service.BehaviorEventService;

import java.time.LocalDateTime;

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

    @PostMapping("/behavior")
    public String receiveBehavior(@RequestBody UserBehavior behavior) {

        String fakeInterest =
                fakeInterestGenerator.generateFakeInterest(behavior.getRealInterest());

        BehaviorEvent event = new BehaviorEvent(
                behavior.getUserId(),
                behavior.getPlatform(),
                behavior.getRealInterest(),
                fakeInterest,
                LocalDateTime.now()
        );

        // Save to MongoDB
        behaviorEventService.saveEvent(event);

        // Send to Kafka
        kafkaProducerService.sendMessage(event.toString());

        return "Behavior saved to MongoDB and streamed to Kafka";
    }

}
