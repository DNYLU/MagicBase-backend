package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.DeckLineCard;
import com.example.magicbasebackend.services.DeckLineCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/dlc")
public class DeckLineCardController {

    private DeckLineCardService deckLineCardService;

    public DeckLineCardController(DeckLineCardService deckLineCardService) {
        this.deckLineCardService = deckLineCardService;
    }

    @PostMapping()
    public ResponseEntity<DeckLineCard> add(@RequestBody AddCardRequestDto addCardRequest) {
        DeckLineCard dlc = deckLineCardService.addDeckCards(addCardRequest);

        // dlc giver null retur pt.
        return new ResponseEntity<>(dlc, HttpStatus.OK);
    }
}