package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.services.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deck")
@CrossOrigin
public class DeckController {
    private DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public ResponseEntity<Deck> addDeck(@RequestBody Deck deck) {
        Deck addedDeck = deckService.add(deck);
        return new ResponseEntity<>(addedDeck, HttpStatus.OK);
    }
}
