package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.models.User;
import com.example.magicbasebackend.services.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add/user")
    public void addUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("username") String username) {
        User user = new User(name, password, username);
        userService.saveUser(user);
    }

    @GetMapping("get/user/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        return user;
    }
}
