package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddDeckRequestDto;
import com.example.magicbasebackend.dto.ShareDeckRequestDto;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.DeckLineCard;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {
    private DeckRepository deckRepository;
    private UserService userService;

    public DeckService(DeckRepository deckRepository, UserService userService) {
        this.deckRepository = deckRepository;
        this.userService = userService;
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

    public Deck add(AddDeckRequestDto deckDto) {
        User user = userService.getUserById(deckDto.getUserId());
        Deck deck = new Deck();
        deck.setName(deckDto.getName());
        deck.setOwner(user);
        deck.setDescription(deckDto.getDescription());
        deck.setFormatType(deckDto.getFormatType());
        deck.addUser(user);
        return deckRepository.save(deck);
    }

    public void deleteById(Deck deck, Long userId){
        User user = userService.getUserById(userId);
        deck.removeUser(user);
        if (deck.getUsers().size() == 0) {
            deckRepository.deleteById(deck.getId());
        }
        else {
            deckRepository.save(deck);
        }

    }

    public Deck shareDeck(ShareDeckRequestDto shareDeckRequestDto) {
        User user = userService.getUserByUsername(shareDeckRequestDto.getUsername()).get();
        Deck deck = deckRepository.findById(shareDeckRequestDto.getContainerId()).get();
        Deck deckCopy = new Deck();
        deckCopy.setName(deck.getName());
        deckCopy.setDescription(deck.getDescription());
        deckCopy.setFormatType(deck.getFormatType());
        deckCopy.addUser(user);
        deckCopy.setOwner(user);
        deckCopy = deckRepository.save(deckCopy);
        List<DeckLineCard> deckLineCards = deck.getDeckLineCards();
        List<DeckLineCard> deckLineCardsCopy = new ArrayList<>();
        for (DeckLineCard dlc:deckLineCards) {
            DeckLineCard deckLineCard = new DeckLineCard();
            deckLineCard.setQuantity(dlc.getQuantity());
            deckLineCard.setCard(dlc.getCard());
            deckLineCard.setDeck(deckCopy);
            deckLineCardsCopy.add(deckLineCard);
        }
        deckCopy.setDeckLineCards(deckLineCardsCopy);
        deckRepository.save(deckCopy);
        return deckCopy;
    }
}
