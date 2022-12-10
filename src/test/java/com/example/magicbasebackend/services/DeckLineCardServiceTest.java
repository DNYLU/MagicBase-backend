package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.DeckLineCard;
import com.example.magicbasebackend.repositories.DeckLineCardRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeckLineCardServiceTest {

    @Autowired
    private DeckLineCardService deckLineCardService;

    @Mock
    private Deck deck;

    private List<DeckLineCard> testDeckLineCards;

    private String testApiId;

    @BeforeAll
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
        Card card1 = new Card();
        card1.setApiId("test123");
        Card card2 = new Card();
        card2.setApiId("test321");
        Card card3 = new Card();
        card3.setApiId("test231");

        DeckLineCard dlc1 = new DeckLineCard();
        dlc1.setCard(card1);

        DeckLineCard dlc2 = new DeckLineCard();
        dlc2.setCard(card2);

        DeckLineCard dlc3 = new DeckLineCard();
        dlc3.setCard(card3);

        testDeckLineCards = new ArrayList(List.of(dlc1, dlc2, dlc3));

        testApiId = "test221";
    }
    @Test
    @DisplayName("Return null when DeckLineCard does not have a card with matching apiId")
    void findDeckLineCard_returnNull() {
        Mockito.when(deck.getDeckLineCards()).thenReturn(testDeckLineCards);

        List<DeckLineCard> deckLineCards = deck.getDeckLineCards();

        DeckLineCard dlc = deckLineCardService.findDeckLineCard(deckLineCards, testApiId);

        assertEquals(
                null,
                dlc,
                "Should be null, since the there is no DeckLineCard with a card with the same apiId");
    }
    @Test
    @DisplayName("Return DeckLineCard when a DecklineCard has a matching apiId")
    void findDeckLineCard_returnDeckLineCard() {
        Mockito.when(deck.getDeckLineCards()).thenReturn(testDeckLineCards);

        List<DeckLineCard> deckLineCards = deck.getDeckLineCards();

        DeckLineCard dlc = deckLineCardService.findDeckLineCard(deckLineCards, "test123");

        assertNotNull(dlc, "Should not be null, since there is a card with matching apiId");

    }
}