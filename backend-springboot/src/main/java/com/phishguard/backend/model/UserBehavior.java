package com.phishguard.backend.model;

public class UserBehavior {

    private String userId;
    private String platform;
    private String realInterest;
    private String timestamp;

    public UserBehavior() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRealInterest() {
        return realInterest;
    }

    public void setRealInterest(String realInterest) {
        this.realInterest = realInterest;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
