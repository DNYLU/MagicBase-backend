package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.repositories.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    public Iterable<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public void add(Card card) {
        cardRepository.save(card);
    }

}
