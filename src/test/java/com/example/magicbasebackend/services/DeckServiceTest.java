package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.ShareDeckRequestDto;
import com.example.magicbasebackend.model.Deck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DeckServiceTest {

    @Autowired
    private DeckService deckService;



    @Test
    public void createNewDeck_whenDeckIsShared() {
        ShareDeckRequestDto shareDeckRequestDto = new ShareDeckRequestDto();
        shareDeckRequestDto.setUsername("user");
        shareDeckRequestDto.setDeckId(1L);

        Deck originalDeck = deckService.getDeckById(shareDeckRequestDto.getDeckId());
        Deck copiedDeck = deckService.shareDeck(shareDeckRequestDto);

        assertNotEquals(
                originalDeck.getId(),
                copiedDeck.getId(),
                "Should not be equal, since it is a copy of the original deck");




    }



}