package com.example.magicbasebackend.model;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.repositories.CardRepository;
import com.example.magicbasebackend.repositories.CollectionLineCardRepository;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionLineCardService {

    private CollectionLineCardRepository collectionLineCardRepository;
    private CardRepository cardRepository;
    private CollectionRepository collectionRepository;

    public CollectionLineCardService(CollectionLineCardRepository collectionLineCardRepository,
                                     CardRepository cardRepository,
                                     CollectionRepository collectionRepository) {
        this.collectionLineCardRepository = collectionLineCardRepository;
        this.cardRepository = cardRepository;
        this.collectionRepository = collectionRepository;
    }

    public void addCards(AddCardRequestDto addCardRequest) {
        Card card = cardRepository.findByApiId(addCardRequest.getApiId());
        Collection collection = collectionRepository.findById(addCardRequest.getCollectionId()).get();
        if (card == null) {
            Card newCard = new Card();
            CollectionLineCard collectionLineCard = new CollectionLineCard();
            collectionLineCard.setQuantity(addCardRequest.getQuantity());
            newCard.setConvertedManaCost(addCardRequest.getConvertedManaCost());
            newCard.setApiId(addCardRequest.getApiId());
            collection.setCollectionLineCards(List.of(collectionLineCard));
            collectionLineCard.setCard(newCard);
            cardRepository.save(newCard);
            collectionRepository.save(collection);
        }
    }
}
