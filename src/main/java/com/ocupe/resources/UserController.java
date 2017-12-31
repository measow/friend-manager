package com.ocupe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.ocupe.models.*;
import com.ocupe.repositories.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    // Create new user
    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User newUser) {
        this.userRepository.save(newUser);
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Get all friendships
    @GetMapping("/friendships")
    public List<Friendship> getAllFriendships() {
        return this.friendshipRepository.findFriendshipsFor(5);
    }

    // Get a single user
    @GetMapping("/users/{userId}")
    public User getSingleUser(@PathVariable int userId) {
        return this.userRepository.findOne(userId);
    }

    /*// Get friends for user
    @GetMapping("/users/{userId}/friends")
    public List<User> getFriendsFor(@PathVariable int userId) {
        return this.userRepository.findFriendsFor(userId);
    }

    // Get others (users excluding friends) for user
    @GetMapping("/users/{userId}/others")
    public List<User> getOthersFor(@PathVariable int userId) {
        return this.userRepository.findOthersFor(userId);
    }*/
}