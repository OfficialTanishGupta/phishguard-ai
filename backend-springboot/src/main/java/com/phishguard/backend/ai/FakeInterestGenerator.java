package com.phishguard.backend.ai;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FakeInterestGenerator {

    private static final List<String> FAKE_INTERESTS = List.of(
            "Gardening tools",
            "Fitness supplements",
            "Luxury watches",
            "Online gambling",
            "Crypto giveaways",
            "Dating apps",
            "Travel deals",
            "Cheap electronics"
    );

    private final Random random = new Random();

    public String generateFakeInterest(String realInterest) {
        // Simple AI logic: return a random unrelated interest
        return FAKE_INTERESTS.get(random.nextInt(FAKE_INTERESTS.size()));
    }
}
