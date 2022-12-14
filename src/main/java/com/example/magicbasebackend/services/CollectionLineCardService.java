package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.repositories.CardRepository;
import com.example.magicbasebackend.repositories.CollectionLineCardRepository;
import com.example.magicbasebackend.repositories.CollectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionLineCardService {

    private CollectionLineCardRepository collectionLineCardRepository;
    private CardRepository cardRepository;
    private CollectionRepository collectionRepository;
    private ModelMapper modelMapper;

    public CollectionLineCardService(CollectionLineCardRepository collectionLineCardRepository,
                                     CardRepository cardRepository,
                                     CollectionRepository collectionRepository,
                                     ModelMapper modelMapper) {
        this.collectionLineCardRepository = collectionLineCardRepository;
        this.cardRepository = cardRepository;
        this.collectionRepository = collectionRepository;
        this.modelMapper = modelMapper;
    }

    public CollectionLineCard addCollectionCards(AddCardRequestDto addCardRequest) {
        Card card = cardRepository.findByApiId(addCardRequest.getApiId());
        Collection collection = collectionRepository.findById(addCardRequest.getContainerId()).get();
        CollectionLineCard collectionLineCard = new CollectionLineCard();
        if (card == null) {
            card = modelMapper.map(addCardRequest, Card.class);

        }
        CollectionLineCard clc = findCollectionLineCard(collection.getCollectionLineCards(), addCardRequest.getApiId());
        if(clc != null) {
            clc.setQuantity(clc.getQuantity() + addCardRequest.getQuantity());
            return collectionLineCardRepository.save(clc);
        }

        collectionLineCard.setCollection(collection);
        collectionLineCard.setQuantity(addCardRequest.getQuantity());
        collectionLineCard.setCard(card);
        cardRepository.save(card);
        collectionRepository.save(collection);
        return collectionLineCardRepository.save(collectionLineCard);
    }

    public CollectionLineCard findCollectionLineCard(List<CollectionLineCard> collectionLineCards, String apiId) {
        CollectionLineCard collectionLineCard = null;
        for(CollectionLineCard clc: collectionLineCards) {
            if (clc.getCard().getApiId().equals(apiId)) {
                collectionLineCard = clc;
            }
        }
        return collectionLineCard;

    }

    public CollectionLineCard update(CollectionLineCard collectionLineCard){
        CollectionLineCard oldCLC = collectionLineCardRepository.findById(collectionLineCard.getId()).get();
        if (collectionLineCard.getQuantity() == 0){
            collectionLineCardRepository.deleteById(collectionLineCard.getId());
            return oldCLC;
        }
        return collectionLineCardRepository.save(oldCLC.updateFrom(collectionLineCard));
    }


    public List<CollectionLineCard> search(String searchWord, Long id) {
        List<CollectionLineCard> allCards = collectionLineCardRepository.findByCollectionId(id);
        List<CollectionLineCard> sortedCards = allCards.stream().filter(clc -> clc.getCard().getName().toLowerCase().contains(searchWord.toLowerCase()))
                .collect(Collectors.toList());

        return sortedCards;
    }
}
