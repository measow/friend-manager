package com.ocupe.resources;

import com.ocupe.models.Friendship;
import com.ocupe.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendshipController {

    @Autowired
    FriendshipRepository friendshipRepository;

    // Delete a friendship
    @DeleteMapping("/friendships/{friendshipId}")
    public ResponseEntity deleteFriend(@PathVariable int friendshipId) {
        Friendship friendship = this.friendshipRepository.findOne(friendshipId);
        if (friendship != null) {
            this.friendshipRepository.delete(friendship);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}