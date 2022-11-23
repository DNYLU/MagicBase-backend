package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.exception.ResourceNotFoundException;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
     public ResponseEntity<User> addUser(@RequestBody User user) {
       return ResponseEntity.ok().body( userService.saveUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity <User> findUserByUsername(@PathVariable("username") String username) {
        Optional<User> foundUser = userService.getUserByUsername(username);
        if (foundUser.isEmpty()) {
            throw new ResourceNotFoundException("User with username: " + username + " was not found");
        }
        return ResponseEntity.ok().body(foundUser.get());
    }
}
