package com.example.magicbasebackend.services;

import com.example.magicbasebackend.enums.CollectionType;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.CollectionRepository;
import com.example.magicbasebackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;
    CollectionRepository collectionRepository;

    public UserService(UserRepository userRepository, CollectionRepository collectionRepository) {
        this.userRepository = userRepository;
        this.collectionRepository = collectionRepository;
    }

    public User saveUser(User user) {
        Collection collection = new Collection();
        collection.setType(CollectionType.ALL_CARDS);
        collection.setName(user.getUsername() + "'s collection");
        collectionRepository.save(collection);

        user.setCollections(Set.of(collection));

       return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
