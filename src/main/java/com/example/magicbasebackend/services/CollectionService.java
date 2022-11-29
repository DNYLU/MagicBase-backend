package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;
    private UserService userService;

    public CollectionService(CollectionRepository collectionRepository, UserService userService) {
        this.collectionRepository = collectionRepository;
        this.userService = userService;
    }

    public Collection addCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection getCollectionById(Long id) {
        return collectionRepository.findById(id).get();
    }

    public List<Collection> showAllCollection(Long id) {
        return collectionRepository.findByUsersId(id);
    }

    public void deleteById(Long id){
        collectionRepository.deleteById(id);
    }
}
