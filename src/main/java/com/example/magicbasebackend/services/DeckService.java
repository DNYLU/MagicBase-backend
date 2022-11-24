package com.example.magicbasebackend.services;

import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.repositories.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {
    private DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public Deck getDeckById(Long id) {
        return deckRepository.findById(id).get();
    }

    public Iterable<Deck> findAllDecks() {
        return deckRepository.findAll();
    }

    public List<Deck> findAllDecksByUserId( Long id){
      return   deckRepository.findByUsersId(id);
    }

    public Deck add(Deck deck) {
        return deckRepository.save(deck);
    }
}
