package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.ShareDeckRequestDto;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.DeckRepository;
import com.example.magicbasebackend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeckServiceTest {

    @Autowired
    private DeckService deckService;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void beforeAll() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@gmail");
        user.setPassword("123123");

        userRepository.save(user);

        Deck deck = new Deck();
        User deckOwner = new User();
        deck.addUser(deckOwner);
        deckRepository.save(deck);

    }
    @Test
    public void createNewDeck_whenDeckIsShared() {
        ShareDeckRequestDto shareDeckRequestDto = new ShareDeckRequestDto();
        shareDeckRequestDto.setUsername("testuser");
        shareDeckRequestDto.setDeckId(1L);

        Deck originalDeck = deckService.getDeckById(shareDeckRequestDto.getDeckId());
        Deck copiedDeck = deckService.shareDeck(shareDeckRequestDto);

        assertNotEquals(
                originalDeck.getId(),
                copiedDeck.getId(),
                "Should not be equal, since it is a copy of the original deck");




    }



}