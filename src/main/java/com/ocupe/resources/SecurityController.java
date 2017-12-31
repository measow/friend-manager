package com.ocupe.resources;

import com.ocupe.models.User;
import com.ocupe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    //log in user
    //on success, returns 200 and creates session cookie
    //on failure, returns 401
    @PostMapping("/security/login")
    public void loginUser(@Valid @RequestBody User newUser) {
        // TODO: implement logic
    }

    // log out user
    //on success, returns 200 and destroys session cookie
    @PostMapping("/security/logout")
    public void logoutUser(@Valid @RequestBody User newUser) {
        // TODO: implement logic
    }
}
