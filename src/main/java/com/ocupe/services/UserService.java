package com.ocupe.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.ocupe.repositories.FriendshipRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.ocupe.models.Friendship;
import com.ocupe.models.User;

@Service
public class UserService {

    @Autowired
    FriendshipRepository friendshipRepository;

    public List<User> getFriendsFor(int userId) {
        List<User> result = new ArrayList<>();
        List<Friendship> requesterFriendships = this.friendshipRepository.findFriendshipsRequestedFrom(userId);
        List<Friendship> requesteeFriendships = this.friendshipRepository.findFriendshipsRequestedTo(userId);

        for (Friendship friendship : requesterFriendships) {
            User friend = friendship.getRequestee();
            result.add(friend);
        }

        for (Friendship friendship : requesteeFriendships) {
            User friend = friendship.getRequester();
            result.add(friend);
        }
        return result;
    }
}