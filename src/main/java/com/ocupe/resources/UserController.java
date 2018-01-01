package com.ocupe.resources;

import com.ocupe.viewModels.FriendView;
import com.ocupe.viewModels.UserProfileView;
import com.ocupe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.ocupe.models.*;
import com.ocupe.repositories.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserService userService;

    // Get all users
    @GetMapping("/users")
    public Iterable<UserProfileView> getAllUsers() {
        List<UserProfileView> result = new ArrayList<>();
        List<User> allUsers = this.userRepository.findAll();

        for (User user : allUsers) {
            result.add(new UserProfileView(user.getUserId(), user.getAlias(), user.getName(), user.getEmail()));
        }

        return result;
    }

    // Create new user
    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User newUser) {
        this.userRepository.save(newUser);
    }

    // Get a single user profile
    @GetMapping("/users/{userId}")
    public UserProfileView getSingleUser(@PathVariable int userId) {
        User user = this.userRepository.findOne(userId);
        return new UserProfileView(user.getUserId(), user.getAlias(), user.getName(), user.getEmail());
    }

    // Get friends for current user
    @GetMapping("/users/{userId}/friends")
    public ResponseEntity getFriendsFor(@PathVariable int userId) {

        List<FriendView> result = new ArrayList<>();
        User currentUser = this.userRepository.getOne(userId);

        if(currentUser == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<Friendship> requesterFriendships = this.friendshipRepository.findFriendshipsRequestedFrom(userId);
        List<Friendship> requesteeFriendships = this.friendshipRepository.findFriendshipsRequestedTo(userId);

        for (Friendship friendship : requesterFriendships) {
            User friend = friendship.getRequestee();
            result.add(new FriendView(friendship.getFriendshipId(), friend.getUserId(), friend.getAlias()));
        }

        for (Friendship friendship : requesteeFriendships) {
            User friend = friendship.getRequester();
            result.add(new FriendView(friendship.getFriendshipId(), friend.getUserId(), friend.getAlias()));
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    // Add a friend to current user
    @PostMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity createFriend(@PathVariable int userId, @PathVariable int friendId) {

        User currentUser = this.userRepository.getOne(userId);
        User friend = this.userRepository.getOne(friendId);

        if(currentUser == null || friend == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<User> friends = this.userService.getFriendsFor(currentUser.getUserId());

        // TODO: investigate why equals override doesn't work
        boolean friendAlreadyExists = false;
        for (User aFriend : friends) {
            if(aFriend.getUserId() == friend.getUserId()) {
                friendAlreadyExists = true;
                break;
            }
        }

        if(friendAlreadyExists) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            Friendship newFriendship = new Friendship(currentUser, friend);
            this.friendshipRepository.save(newFriendship);

            return new ResponseEntity(
                    new FriendView(newFriendship.getFriendshipId(), friend.getUserId(), friend.getAlias()),
                    HttpStatus.CREATED);
        }
    }

    // Get others (users excluding friends) for user
    @GetMapping("/users/{userId}/others")
    public ResponseEntity getOthersFor(@PathVariable int userId) {

        User currentUser = this.userRepository.getOne(userId);

        if(currentUser == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<UserProfileView> result = new ArrayList<>();
        List<User> allUsers = this.userRepository.findAll();
        List<User> friends = this.userService.getFriendsFor(currentUser.getUserId());

        for (User user : allUsers) {
            if (user != currentUser && !friends.contains(user)) {
                result.add(new UserProfileView(user.getUserId(), user.getAlias(), user.getName(), user.getEmail()));
            }
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }
}