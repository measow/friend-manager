package com.ocupe.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import com.ocupe.models.*;
import com.ocupe.repositories.*;
import com.ocupe.viewModels.FriendView;
import com.ocupe.viewModels.UserProfileView;
import com.ocupe.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserService userService;

    // Create new user
    @PostMapping("/users")
    public ResponseEntity<UserProfileView> createUser(@Valid @RequestBody User newUser) {
        // TODO: add validation
        UserProfileView result;
        this.userRepository.save(newUser);

        result = new UserProfileView(newUser.getUserId(), newUser.getAlias(),
                newUser.getName(), newUser.getEmail(), newUser.getDateOfBirth());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping("/users")
    public Iterable<UserProfileView> getAllUsers() {
        List<UserProfileView> result = new ArrayList<>();
        List<User> allUsers = this.userRepository.findAll();

        for (User user : allUsers) {
            result.add(new UserProfileView(user.getUserId(), user.getAlias(),
                    user.getName(), user.getEmail(), user.getDateOfBirth()));
        }

        return result;
    }

    // Get a single user profile
    @GetMapping("/users/{userId}")
    public UserProfileView getSingleUser(@PathVariable int userId) {
        User user = this.userRepository.findOne(userId);
        return new UserProfileView(user.getUserId(), user.getAlias(),
                user.getName(), user.getEmail(), user.getDateOfBirth());
    }

    // Get friends for current user
    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<FriendView>> getFriendsFor(@PathVariable int userId) {

        List<FriendView> result = new ArrayList<>();
        User currentUser = this.userRepository.getOne(userId);

        if(currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Add a friend to current user
    @PostMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity<FriendView> createFriend(@PathVariable int userId, @PathVariable int friendId) {

        User currentUser = this.userRepository.getOne(userId);
        User friend = this.userRepository.getOne(friendId);

        if(currentUser == null || friend == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Friendship newFriendship = new Friendship(currentUser, friend);
            this.friendshipRepository.save(newFriendship);

            return new ResponseEntity<>(
                    new FriendView(newFriendship.getFriendshipId(), friend.getUserId(), friend.getAlias()),
                    HttpStatus.CREATED);
        }
    }

    // Get others (users excluding friends) for user
    @GetMapping("/users/{userId}/others")
    public ResponseEntity<List<UserProfileView>> getOthersFor(@PathVariable int userId) {

        User currentUser = this.userRepository.getOne(userId);

        if(currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<UserProfileView> result = new ArrayList<>();
        List<User> allUsers = this.userRepository.findAll();
        List<User> friends = this.userService.getFriendsFor(currentUser.getUserId());

        for (User user : allUsers) {
            if (user != currentUser && !friends.contains(user)) {
                result.add(new UserProfileView(user.getUserId(), user.getAlias(),
                        user.getName(), user.getEmail(), user.getDateOfBirth()));
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}