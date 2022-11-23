package com.example.magicbasebackend.controller;

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
    public ResponseEntity <User> findUserByUsername(@PathVariable("username") String username) throws Exception {
        Optional<User> user = Optional.of(userService.getUserByUsername(username)
                .orElseThrow(() -> new Exception()));
        return ResponseEntity.ok().body(user.get());
    }
}
