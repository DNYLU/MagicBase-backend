package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.repositories.CardRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollectionLineCardServiceTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CollectionLineCardService collectionLineCardService;


    private AddCardRequestDto addCardRequestDto = new AddCardRequestDto(
            "test123",
            "name",
            "oracle",
            "uncommon",
            "instant",
            0,
            2,
            5,
            "set",
            10.5,
            "sfdsfdfsf",
            4,
            1L);

    private Card card;

    private List<Card> allCardsInDatabase;


    @Test
    @Order(1)
    @DisplayName("No Cards in database")
    public void noCardsInDatabase() {
        allCardsInDatabase = (List<Card>) cardRepository.findAll();
        assertEquals(0, allCardsInDatabase.size(), "The list size should be 0");
    }

    @Test
    @Order(2)
    @DisplayName("addCards - card does not exist")
    public void addCards_cardDoesNotExist() {
        collectionLineCardService.addCollectionCards(addCardRequestDto);
        allCardsInDatabase = (List<Card>) cardRepository.findAll();
        assertEquals(1, allCardsInDatabase.size(), "The list size should be 1");
    }

    @Test
    @Order(3)
    @DisplayName("Find card with apiId")
    public void findCardWithApiId() {
        card = cardRepository.findByApiId(addCardRequestDto.getApiId());
        assertEquals(addCardRequestDto.getApiId(), card.getApiId() , "Should be equal");
    }

    @Test
    @Order(4)
    @DisplayName("addCards - card already exists")
    public void addCards_cardAlreadyExists() {
        collectionLineCardService.addCollectionCards(addCardRequestDto);
        allCardsInDatabase = (List<Card>) cardRepository.findAll();
        assertEquals(1, allCardsInDatabase.size(), "The list size should be 1");


    }
    @Test
    @Order(5)
    @DisplayName("addCards - add card with unique apiId")
    public void addCards_addUniqueCard() {
        addCardRequestDto.setApiId("fgfgfg");
        collectionLineCardService.addCollectionCards(addCardRequestDto);
        allCardsInDatabase = (List<Card>) cardRepository.findAll();
        assertEquals(2, allCardsInDatabase.size(), "The list size should be 2");

    }


}