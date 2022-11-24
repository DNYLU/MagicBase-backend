package com.example.magicbasebackend;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.Deck;
import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.CollectionRepository;
import com.example.magicbasebackend.repositories.DeckRepository;
import com.example.magicbasebackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MagicBaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicBaseBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(
            UserRepository userRepository,
            CollectionRepository collectionRepository,
            DeckRepository deckRepository
    )
    {
        return (args) -> {

            User bobsen = new User("bobsen@gmail", "123123", "bobsen");
            userRepository.save(bobsen);

            Collection collection = new Collection();
            collection.setUsers(List.of(bobsen));
            collectionRepository.save(collection);

            bobsen.setCollections(List.of(collection));
            userRepository.save(bobsen);


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
