package com.ocupe.viewModels;

public class UserProfileView {
    private int userId;
    private String alias;
    private String name;
    private String email;

    public UserProfileView(int userId, String alias, String name, String email) {
        this.userId = userId;
        this.alias = alias;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}