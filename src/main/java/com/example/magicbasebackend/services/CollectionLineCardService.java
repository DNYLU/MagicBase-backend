package com.example.magicbasebackend.services;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.CollectionLineCard;
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
        CollectionLineCard collectionLineCard = new CollectionLineCard();
        if (card == null) {
            card = new Card();


            card.setConvertedManaCost(addCardRequest.getConvertedManaCost());
            card.setApiId(addCardRequest.getApiId());
            card.setEuroPrice(addCardRequest.getEuroPrice());
            card.setImageUrl(addCardRequest.getImageUrl());
            card.setName(addCardRequest.getName());
            card.setOracleText(addCardRequest.getOracleText());
            card.setPower(addCardRequest.getPower());
            card.setRarity(addCardRequest.getRarity());
            card.setSetName(addCardRequest.getSetName());
            card.setToughness(addCardRequest.getToughness());
            card.setTypeLine(addCardRequest.getTypeLine());
            System.out.println(addCardRequest);



        }

        collectionLineCard.setCollection(collection);
        collectionLineCard.setQuantity(addCardRequest.getQuantity());
        collectionLineCard.setCard(card);
        cardRepository.save(card);

        collectionRepository.save(collection);
        collectionLineCardRepository.save(collectionLineCard);
    }
}
