package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Add user - create default collection")
    void addDefaultCollection_whenUserCreated() {
        User user = new User("bob@gmail.com", "123", "bob");
        userService.saveUser(user);

        User newlyCreatedUser = userService.getUserByUsername(user.getUsername()).get();
        List<Collection> collections = newlyCreatedUser.getCollections();

        assertEquals(1, collections.size());


    }
}