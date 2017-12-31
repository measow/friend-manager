package com.ocupe.models;

import javax.persistence.*;

@Entity
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int friendshipId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private User requester;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private User requestee;

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getRequestee() {
        return requestee;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }
}