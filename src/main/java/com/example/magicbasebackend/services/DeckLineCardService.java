package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.DeckLineCard;
import com.example.magicbasebackend.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckLineCardService {

    private DeckLineCardRepository deckLineCardRepository;
    private CardRepository cardRepository;
    private DeckRepository deckRepository;
    private ModelMapper modelMapper;

    public DeckLineCardService(DeckLineCardRepository deckLineCardRepository,
                               CardRepository cardRepository,
                               DeckRepository deckRepository,
                               ModelMapper modelMapper) {
        this.deckLineCardRepository = deckLineCardRepository;
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
        this.modelMapper = modelMapper;
    }

    public DeckLineCard addDeckCards(AddCardRequestDto addCardRequest) {
        Card card = cardRepository.findByApiId(addCardRequest.getApiId());
        Deck deck = deckRepository.findById(addCardRequest.getContainerId()).get();
        DeckLineCard deckLineCard = new DeckLineCard();
        if (card == null) {
            card = modelMapper.map(addCardRequest, Card.class);
        }
        DeckLineCard dlc = findDeckLineCard(deck.getDeckLineCards(), addCardRequest.getApiId());
        if(dlc != null) {
            dlc.setQuantity(dlc.getQuantity() + addCardRequest.getQuantity());
            return deckLineCardRepository.save(dlc);
        }

        deckLineCard.setDeck(deck);
        deckLineCard.setQuantity(addCardRequest.getQuantity());
        deckLineCard.setCard(card);
        cardRepository.save(card);
        deckRepository.save(deck);
        return deckLineCardRepository.save(deckLineCard);
    }
    public DeckLineCard findDeckLineCard(List<DeckLineCard> deckLineCards, String apiId) {
        DeckLineCard deckLineCard = null;
        for(DeckLineCard dlc: deckLineCards) {
            if (dlc.getCard().getApiId().equals(apiId)) {
                deckLineCard = dlc;
            }

        }
        return deckLineCard;

    }


    public DeckLineCard update(DeckLineCard deckLineCard){
        DeckLineCard oldCLC = deckLineCardRepository.findById(deckLineCard.getId()).get();
        if (deckLineCard.getQuantity() == 0){
            deckLineCardRepository.deleteById(deckLineCard.getId());
            return oldCLC;
        }
        return deckLineCardRepository.save(oldCLC.updateFrom(deckLineCard));
    }
}
