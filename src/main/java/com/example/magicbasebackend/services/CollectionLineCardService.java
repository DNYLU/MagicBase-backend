package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.repositories.CardRepository;
import com.example.magicbasebackend.repositories.CollectionLineCardRepository;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionLineCardService {

    private CollectionLineCardRepository collectionLineCardRepository;
    private CardRepository cardRepository;
    private CollectionRepository collectionRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CollectionLineCardService(CollectionLineCardRepository collectionLineCardRepository,
                                     CardRepository cardRepository,
                                     CollectionRepository collectionRepository) {
        this.collectionLineCardRepository = collectionLineCardRepository;
        this.cardRepository = cardRepository;
        this.collectionRepository = collectionRepository;
    }

    public CollectionLineCard addCollectionCards(AddCardRequestDto addCardRequest) {
        Card card = cardRepository.findByApiId(addCardRequest.getApiId());
        Collection collection = collectionRepository.findById(addCardRequest.getDeckId()).get();
        CollectionLineCard collectionLineCard = new CollectionLineCard();
        if (card == null) {
            card = modelMapper.map(addCardRequest, Card.class);

        }

        collectionLineCard.setCollection(collection);
        collectionLineCard.setQuantity(addCardRequest.getQuantity());
        collectionLineCard.setCard(card);
        cardRepository.save(card);
        collectionRepository.save(collection);
        return collectionLineCardRepository.save(collectionLineCard);
    }
}
