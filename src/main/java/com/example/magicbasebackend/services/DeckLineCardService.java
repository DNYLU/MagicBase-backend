package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.DeckLineCard;
import com.example.magicbasebackend.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckLineCardService {

    private DeckLineCardRepository deckLineCardRepository;
    private CardRepository cardRepository;
    private DeckRepository deckRepository;
    @Autowired
    private ModelMapper modelMapper;

    public DeckLineCardService(DeckLineCardRepository deckLineCardRepository, CardRepository cardRepository, DeckRepository deckRepository) {
        this.deckLineCardRepository = deckLineCardRepository;
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }

    public DeckLineCard addDeckCards(AddCardRequestDto addCardRequest) {
        Card card = cardRepository.findByApiId(addCardRequest.getApiId());
        Deck deck = deckRepository.findById(addCardRequest.getDeckId()).get();
        DeckLineCard deckLineCard = new DeckLineCard();
        if (card == null) {
            card = modelMapper.map(addCardRequest, Card.class);
        }

        deckLineCard.setDeck(deck);
        deckLineCard.setQuantity(addCardRequest.getQuantity());
        deckLineCard.setCard(card);
        cardRepository.save(card);
        deckRepository.save(deck);
        return deckLineCardRepository.save(deckLineCard);
    }
}
