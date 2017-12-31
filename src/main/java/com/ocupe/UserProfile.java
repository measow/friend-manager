package com.ocupe;

public class UserProfile {
    private int userId;
    private String alias;

    public UserProfile(int userId, String alias) {
        this.userId = userId;
        this.alias = alias;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}