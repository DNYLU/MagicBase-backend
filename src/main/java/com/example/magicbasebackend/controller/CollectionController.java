package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/collection")
public class CollectionController {
    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/add/{collectionId}/cards/{cardId}/user/{userName}")
    public ResponseEntity<Collection> addCard(@RequestBody Collection collection,
                                              @PathVariable("collectionId") Long collectionId,
                                              @PathVariable("cardId") Long cardId,
                                              @PathVariable("userName") String userName) {
        return new ResponseEntity<>(collectionService.addCard(collection, collectionId, cardId, userName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(collectionService.getCollectionById(id), HttpStatus.OK);
    }
}
