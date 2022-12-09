package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCollectionRequestDto;
import com.example.magicbasebackend.dto.ShareCollectionRequestDto;
import com.example.magicbasebackend.model.*;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        collection.setOwner(user);
        collection.addUser(user);

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


    public Collection shareCollection(ShareCollectionRequestDto shareCollectionRequestDto) {
        User user = userService.getUserByUsername(shareCollectionRequestDto.getUsername()).get();
        Collection collection = collectionRepository.findById(shareCollectionRequestDto.getContainerId()).get();
        Collection collectionCopy = new Collection();
        collectionCopy.setName(collection.getName());
        collectionCopy.setDescription(collection.getDescription());
        collectionCopy.setType(collection.getType());
        collectionCopy.addUser(user);
        collectionCopy.setOwner(user);
        collectionCopy = collectionRepository.save(collectionCopy);
        List<CollectionLineCard> collectionLineCards = collection.getCollectionLineCards();
        List<CollectionLineCard> deckLineCardsCopy = new ArrayList<>();
        for (CollectionLineCard clc:collectionLineCards) {
            CollectionLineCard collectionLineCard = new CollectionLineCard();
            collectionLineCard.setQuantity(clc.getQuantity());
            collectionLineCard.setCard(clc.getCard());
            collectionLineCard.setCollection(collectionCopy);
            deckLineCardsCopy.add(collectionLineCard);
        }
        collectionCopy.setCollectionLineCards(deckLineCardsCopy);
        collectionRepository.save(collectionCopy);
        return collectionCopy;
    }
}

