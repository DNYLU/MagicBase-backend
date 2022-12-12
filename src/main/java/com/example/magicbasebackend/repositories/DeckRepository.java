package com.example.magicbasebackend.repositories;

import com.example.magicbasebackend.model.Collection;
import com.example.magicbasebackend.model.Deck;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeckRepository extends CrudRepository<Deck, Long> {

    List<Deck> findByUsersId(Long userId);
    List<Deck> findAllByHasBeenSetToPublicIsTrue();
}
