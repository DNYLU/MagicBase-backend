package com.example.magicbasebackend.controller;


import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.services.CollectionLineCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/clc")
public class CollectionLineCardController {

    private CollectionLineCardService collectionLineCardService;

    public CollectionLineCardController(CollectionLineCardService collectionLineCardService) {
        this.collectionLineCardService = collectionLineCardService;
    }

    @PostMapping()
    public ResponseEntity<CollectionLineCard> add(@RequestBody AddCardRequestDto addCardRequest) {
        CollectionLineCard clc = collectionLineCardService.addCollectionCards(addCardRequest);

        // skal lige ændres til at returnere det rigtige
        return new ResponseEntity<>(clc, HttpStatus.OK);
    }


    @PatchMapping()
    public ResponseEntity<CollectionLineCard> update(@RequestBody CollectionLineCard collectionLineCard){
        return ResponseEntity.ok().body(collectionLineCardService.update(collectionLineCard));
    }
    @GetMapping("/search/{collectionId}")
    public ResponseEntity<List<CollectionLineCard>> search(@RequestParam(name = "search-word") String searchWord, @PathVariable("collectionId") Long id){
        return new ResponseEntity<>(collectionLineCardService.search(searchWord,id),HttpStatus.OK);
    }
}

