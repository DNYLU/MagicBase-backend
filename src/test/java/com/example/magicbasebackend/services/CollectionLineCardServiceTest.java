package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.repositories.CardRepository;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"job.autorun.command-line-runner=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollectionLineCardServiceTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionLineCardService collectionLineCardService;

    private AddCardRequestDto addCardRequestDto;

    @BeforeAll
    public void beforeALl() {
        addCardRequestDto = new AddCardRequestDto(
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
        Collection collection = new Collection();
        collectionRepository.save(collection);
    }

    @Test
    @Order(1)
    @DisplayName("No Cards in database")
    public void noCardsInDatabase() {
        List<Card> cards = (List<Card>) cardRepository.findAll();
        assertEquals(0, cards.size(), "The list size should be 0");
    }

    @Test
    @Order(2)
    @DisplayName("addCards - card does not exist")
    public void addCards_cardDoesNotExist() {
        collectionLineCardService.addCollectionCards(addCardRequestDto);
       List<Card> cards = (List<Card>) cardRepository.findAll();
        assertEquals(1, cards.size(), "The list size should be 1");
    }

    @Test
    @Order(3)
    @DisplayName("Find card with apiId")
    public void findCardWithApiId() {
        Card card = cardRepository.findByApiId(addCardRequestDto.getApiId());
        assertEquals(addCardRequestDto.getApiId(), card.getApiId(), "Should be equal");
    }

    @Test
    @Order(4)
    @DisplayName("addCards - card already exists")
    public void addCards_cardAlreadyExists() {
        collectionLineCardService.addCollectionCards(addCardRequestDto);
        List<Card> cards = (List<Card>) cardRepository.findAll();
        assertEquals(1, cards.size(), "The list size should be 1");


    }
    @Test
    @Order(5)
    @DisplayName("addCards - add card with unique apiId")
    public void addCards_addUniqueCard() {
        addCardRequestDto.setApiId("fgfgfg");
        collectionLineCardService.addCollectionCards(addCardRequestDto);
        List<Card> cards = (List<Card>) cardRepository.findAll();
        assertEquals(2, cards.size(), "The list size should be 2");

    }


}