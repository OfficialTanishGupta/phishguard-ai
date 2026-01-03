package com.phishguard.backend.ai;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class FakeInterestGenerator {

    private static final Map<String, List<String>> INTEREST_MAP = Map.of(
            "technology", List.of("smart home devices", "fitness wearables", "online productivity tools"),
            "finance", List.of("personal budgeting", "travel deals", "subscription services"),
            "health", List.of("yoga routines", "nutrition plans", "mental wellness apps"),
            "education", List.of("language learning", "online certifications", "career coaching"),
            "shopping", List.of("home decor", "kitchen gadgets", "fashion accessories")
    );

    private final Random random = new Random();

    public String generateFakeInterest(String realInterest) {

        String normalized = realInterest.toLowerCase();

        String bucket = INTEREST_MAP.keySet()
                .stream()
                .filter(normalized::contains)
                .findFirst()
                .orElse("shopping"); // safe default

        List<String> fakeOptions = INTEREST_MAP.get(bucket);

        return fakeOptions.get(random.nextInt(fakeOptions.size()));
    }
}
