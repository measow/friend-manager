package com.ocupe.services;

import com.ocupe.UserProfile;
import com.ocupe.models.Friendship;
import com.ocupe.models.User;
import com.ocupe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ocupe.repositories.FriendshipRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    public List<UserProfile> getAll() {
        List<UserProfile> result = new ArrayList<>();
        List<User> allUsers = this.userRepository.findAll();

        for (User user : allUsers) {
            result.add(new UserProfile(user.getUserId(), user.getAlias()));
        }

        return result;
    }

    public UserProfile getUser(int userId) {
        User user = this.userRepository.findOne(userId);
        return new UserProfile(user.getUserId(), user.getAlias());
    }

    public List<UserProfile> getFriendsFor(int userId) {
        List<UserProfile> result = new ArrayList<>();
        List<Friendship> requesterFriendships = this.friendshipRepository.findFriendshipsRequestedFrom(userId);
        List<Friendship> requesteeFriendships = this.friendshipRepository.findFriendshipsRequestedTo(userId);

        for (Friendship friendship : requesterFriendships) {
            User friend = friendship.getRequestee();
            result.add(new UserProfile(friend.getUserId(), friend.getAlias()));
        }

        for (Friendship friendship : requesteeFriendships) {
            User friend = friendship.getRequester();
            result.add(new UserProfile(friend.getUserId(), friend.getAlias()));
        }
        return result;
    }


    public List<UserProfile> getOthersFor(int userId) {
        List<UserProfile> result = new ArrayList<>();

        Iterable<UserProfile> allUsers = this.getAll();
        List<UserProfile> friends = this.getFriendsFor(userId);

        for (UserProfile userProfile : allUsers) {
            if (userProfile.getUserId() != userId && !friends.contains(userProfile)) {
                result.add(userProfile);
            }
        }

        return result;
    }
}