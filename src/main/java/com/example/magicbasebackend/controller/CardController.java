package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@CrossOrigin
public class CardController {
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        Card card = cardService.getCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Card>> getAllCards() {
        Iterable<Card> cards = cardService.findAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
