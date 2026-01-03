package com.phishguard.backend.ai;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class FakeInterestGeneratorV2 {

    private final Random random = new Random();

    private final List<String> tech = List.of(
            "AI productivity tools",
            "cloud security basics",
            "developer laptops"
    );

    private final List<String> travel = List.of(
            "budget flights to Europe",
            "solo travel tips",
            "travel insurance plans"
    );

    public String generateFakeInterest(String realInterest) {

        // 80% related, 20% random
        if (random.nextInt(100) < 80) {
            return tech.get(random.nextInt(tech.size()));
        } else {
            return travel.get(random.nextInt(travel.size()));
        }
    }
}
