package com.ocupe.viewModels;

public class FriendView {

    private int friendshipId;
    private int userId;
    private String alias;

    public FriendView(int friendshipId, int userId, String alias) {
        this.friendshipId = friendshipId;
        this.userId = userId;
        this.alias = alias;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
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