package com.example.magicbasebackend.controller;


import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.services.CollectionLineCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/clc")
public class CollectionLineCardController {

    private CollectionLineCardService collectionLineCardService;

    public CollectionLineCardController(CollectionLineCardService collectionLineCardService) {
        this.collectionLineCardService = collectionLineCardService;
    }

    @PostMapping()
    public ResponseEntity<AddCardRequestDto> add(@RequestBody AddCardRequestDto addCardRequest) {
        collectionLineCardService.addCards(addCardRequest);

        // skal lige ændres til at returnere det rigtige
        return new ResponseEntity<>(addCardRequest, HttpStatus.OK);



    }

}
