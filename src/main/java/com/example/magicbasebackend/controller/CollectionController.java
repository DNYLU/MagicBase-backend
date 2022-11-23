package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/collection")
public class CollectionController {
    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping()
    public ResponseEntity<Collection> addCollection(@RequestBody Collection collection) {
        return new ResponseEntity<>(collectionService.addCollection(collection), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(collectionService.getCollectionById(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Collection>> showAllCollection(@PathVariable("userId")Long id) {
        return new ResponseEntity<>(collectionService.showAllCollection(id), HttpStatus.OK);
    }
}
