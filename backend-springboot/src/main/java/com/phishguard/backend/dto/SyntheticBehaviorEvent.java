package com.phishguard.backend.dto;

import java.time.LocalDateTime;

public class SyntheticBehaviorEvent {

    private String userId;
    private String persona;
    private String fakeInterest;
    private boolean isSynthetic;
    private LocalDateTime timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getFakeInterest() {
        return fakeInterest;
    }

    public void setFakeInterest(String fakeInterest) {
        this.fakeInterest = fakeInterest;
    }

    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setSynthetic(boolean synthetic) {
        isSynthetic = synthetic;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

