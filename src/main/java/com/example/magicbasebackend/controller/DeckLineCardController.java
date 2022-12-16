package com.example.magicbasebackend.controller;

import com.example.magicbasebackend.dto.AddCardRequestDto;
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
        return new ResponseEntity<>(dlc, HttpStatus.OK);
    }


    @PatchMapping()
    public ResponseEntity<DeckLineCard> update(@RequestBody DeckLineCard deckLineCard){
        return ResponseEntity.ok().body(deckLineCardService.update(deckLineCard));
    }
}