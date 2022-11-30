package com.example.magicbasebackend;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.enums.CollectionType;
import com.example.magicbasebackend.model.*;
import com.example.magicbasebackend.repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MagicBaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicBaseBackendApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap propertyMap = new PropertyMap<AddCardRequestDto, Card>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        };

        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    @Bean
    public CommandLineRunner importData(
            UserRepository userRepository,
            CollectionRepository collectionRepository,
            DeckRepository deckRepository,
            CardRepository cardRepository,
            CollectionLineCardRepository collectionLineCardRepository
    )
    {
        return (args) -> {

            User bobsen = new User("bobsen@gmail", "123123", "bobsen");
            userRepository.save(bobsen);
            User dude = new User("dude@gmail.com", "123123", "dude");
            userRepository.save(dude);

            Collection collection = new Collection();
            collection.setUsers(Set.of(bobsen, dude));
            collection.setDescription("Mit fire collection");
            collection.setName("FIRE");
            collection.setType(CollectionType.ALL_CARDS);
            collectionRepository.save(collection);

            dude.setCollections(Set.of(collection));
            bobsen.setCollections(Set.of(collection));
            userRepository.save(bobsen);
            userRepository.save(dude);

            Card card = new Card();
            card.setApiId("ashogjhadnwnmt");
            card.setName("Fireball");
            card.setOracleText("EN STOR FIREBALL");
            card.setRarity("Rare");
            card.setTypeLine("Creature");
            card.setPower(22);
            card.setToughness(12);
            card.setConvertedManaCost(2);
            card.setSetName("SETNAMAE");
            card.setEuroPrice(2.2);
            card.setImageUrl("https://cards.scryfall.io/png/front/6/f/6f471133-db82-4610-81fb-736fbd3b1c6c.png?1645740909");
            cardRepository.save(card);

            CollectionLineCard collectionLineCard = new CollectionLineCard();
            collectionLineCard.setQuantity(2);
            collectionLineCard.setCollection(collection);
            collectionLineCard.setCard(card);
            //collection
            collectionLineCardRepository.save(collectionLineCard);

            Deck myDeck= new Deck();
            myDeck.setUsers(List.of(bobsen));
            myDeck.setName("myDeck");
            myDeck.setDescription("what ever");
            myDeck.setFormatType("idk");
            myDeck.setPublic(false);
            myDeck.setOwner(bobsen);
            deckRepository.save(myDeck);

            bobsen.setDecks(List.of(myDeck));
            userRepository.save(bobsen);

        };}

}
