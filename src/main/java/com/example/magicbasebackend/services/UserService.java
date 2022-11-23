package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
       return userRepository.save(user);

    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
