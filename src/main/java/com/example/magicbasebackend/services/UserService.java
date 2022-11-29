package com.example.magicbasebackend.services;

import com.example.magicbasebackend.enums.CollectionType;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        Collection collection = new Collection();
        collection.setType(CollectionType.ALL_CARDS);
        collection.setName(user.getUsername() + "'s collection");
        user.setCollections(List.of(collection));
       return userRepository.save(user);
    }

    public User save(User user){
       return userRepository.save(user);
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
