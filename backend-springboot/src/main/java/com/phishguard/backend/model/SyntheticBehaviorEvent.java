package com.phishguard.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "synthetic_behavior_event")
public class SyntheticBehaviorEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String fakeInterest;
    private LocalDateTime timestamp;

    public SyntheticBehaviorEvent() {}

    public SyntheticBehaviorEvent(String userId, String fakeInterest, LocalDateTime timestamp) {
        this.userId = userId;
        this.fakeInterest = fakeInterest;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public String getFakeInterest() { return fakeInterest; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setFakeInterest(String fakeInterest) { this.fakeInterest = fakeInterest; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
