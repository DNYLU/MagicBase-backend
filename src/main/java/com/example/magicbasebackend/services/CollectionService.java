package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCollectionRequestDto;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.User;
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

    public Collection addCollection(AddCollectionRequestDto addCollectionRequestDto) {
        User user = userService.getUserById(addCollectionRequestDto.getUserId());
        Collection collection = new Collection();
        collection.setType(addCollectionRequestDto.getType());
        collection.setName(addCollectionRequestDto.getName());
        collection.setDescription(addCollectionRequestDto.getDescription());
        collection.getUsers().add(user);
        user.setCollections(List.of(collection));
        //userService.save(user);
        return collectionRepository.save(collection);
    }

    public Collection getCollectionById(Long id) {
        return collectionRepository.findById(id).get();
    }

    public List<Collection> showAllCollection(Long id) {
        return collectionRepository.findByUsersId(id);
    }

    public Collection deleteById(Long id, Long userId){
        User user = userService.getUserById(userId);
        Collection collection = collectionRepository.findById(id).get();
        collection.removeUser(user);

        if (collection.getUsers().size() == 0) {
            collectionRepository.deleteById(id);
        }
        else {
            collectionRepository.save(collection);
        }
        return collection;

    }
}
