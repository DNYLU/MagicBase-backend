package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;
    private UserService userService;

    public CollectionService(CollectionRepository collectionRepository, UserService userService) {
        this.collectionRepository = collectionRepository;
        this.userService = userService;
    }

    public Collection addCollection
}
