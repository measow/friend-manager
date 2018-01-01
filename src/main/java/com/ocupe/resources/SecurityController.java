package com.ocupe.resources;

import com.ocupe.models.User;
import com.ocupe.repositories.UserRepository;
import com.ocupe.viewModels.UserCredentialsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/security/validate")
    public ResponseEntity validateUser(@Valid @RequestBody UserCredentialsView credentials) {
        User user = this.userRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if(user == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}