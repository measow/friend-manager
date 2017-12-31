package com.ocupe.resources;

import com.ocupe.viewModels.UserProfile;
import com.ocupe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.ocupe.models.*;
import com.ocupe.repositories.*;

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
    public void createUser(@Valid @RequestBody User newUser) {
        this.userRepository.save(newUser);
    }

    // Get all users
    @GetMapping("/users")
    public Iterable<UserProfile> getAllUsers() {
        return this.userService.getAll();
    }

    // Get a single user
    @GetMapping("/users/{userId}")
    public UserProfile getSingleUser(@PathVariable int userId) {
        return this.userService.getUser(userId);
    }

    // Get friends for user
    @GetMapping("/users/{userId}/friends")
    public Iterable<UserProfile> getFriendsFor(@PathVariable int userId) {
        return this.userService.getFriendsFor(userId);
    }

    // Create a friend
    @PostMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity createFriend(@PathVariable int userId, @PathVariable int friendId) {
        // TODO: find user, add friend
        return new ResponseEntity(HttpStatus.OK);
    }

    // Delete a friend
    @DeleteMapping("/users/{userId}/friends/{friendId}")
    public ResponseEntity deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
        // TODO: find user, delete friend
        return new ResponseEntity(HttpStatus.OK);
    }

    // Get others (users excluding friends) for user
    @GetMapping("/users/{userId}/others")
    public Iterable<UserProfile> getOthersFor(@PathVariable int userId) {
        return this.userService.getOthersFor(userId);
    }
}