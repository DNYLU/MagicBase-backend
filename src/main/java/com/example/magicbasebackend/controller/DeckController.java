package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.dto.AddDeckRequestDto;
import com.example.magicbasebackend.dto.ShareDeckRequestDto;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.services.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deck")
@CrossOrigin
public class DeckController {
    private DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public ResponseEntity<Deck> addDeck(@RequestBody AddDeckRequestDto deckDto) {
        Deck addedDeck = deckService.add(deckDto);
        return new ResponseEntity<>(addedDeck, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    private ResponseEntity<List<Deck>> getDecksByUserId(@PathVariable("userId") Long id){
       List<Deck> deckList = deckService.findAllDecksByUserId(id);
        return ResponseEntity.ok().body(deckList);
    }
    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Deck> deleteDeckById(@PathVariable("id") Long id,@PathVariable("userId") Long userId){
        Deck deck = deckService.getDeckById(id);
        deckService.deleteById(deck, userId);
        return new ResponseEntity<>(deck,HttpStatus.OK);
    }

    @PostMapping("/share")
    public ResponseEntity<Deck> shareDeck(@RequestBody ShareDeckRequestDto shareDeckRequestDto){
        Deck deck = deckService.shareDeck(shareDeckRequestDto);
        return new ResponseEntity<>(deck,HttpStatus.OK);
    }
}
