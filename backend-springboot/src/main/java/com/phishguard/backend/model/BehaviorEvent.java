package com.phishguard.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "behavior_events")
public class BehaviorEvent {

    @Id
    private String id;

    private String userId;
    private String platform;
    private String realInterest;
    private String fakeInterest;
    private LocalDateTime timestamp;

    public BehaviorEvent() {}

    public BehaviorEvent(String userId, String platform,
                         String realInterest, String fakeInterest,
                         LocalDateTime timestamp) {
        this.userId = userId;
        this.platform = platform;
        this.realInterest = realInterest;
        this.fakeInterest = fakeInterest;
        this.timestamp = timestamp;
    }

    // Getters & Setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getPlatform() { return platform; }
    public String getRealInterest() { return realInterest; }
    public String getFakeInterest() { return fakeInterest; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(String id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setPlatform(String platform) { this.platform = platform; }
    public void setRealInterest(String realInterest) { this.realInterest = realInterest; }
    public void setFakeInterest(String fakeInterest) { this.fakeInterest = fakeInterest; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
