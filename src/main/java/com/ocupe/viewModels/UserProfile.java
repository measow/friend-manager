package com.ocupe.viewModels;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (userId != that.userId) return false;
        return alias.equals(that.alias);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + alias.hashCode();
        return result;
    }
}