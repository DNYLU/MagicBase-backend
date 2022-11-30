package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.services.CardService;
import com.example.magicbasebackend.services.DeckLineCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/addcardtodeck")
    public void addCardToDeck(@RequestBody Card card) {
        cardService.add(card);
    }

    @PostMapping()
    public ResponseEntity<Card> add(@RequestBody AddCardRequestDto addCardRequest) {
        Card card = DeckLineCardService.addCards(addCardRequest);

        // skal lige ændres til at returnere det rigtige
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
