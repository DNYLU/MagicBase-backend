package com.example.magicbasebackend.repositories;

import com.example.magicbasebackend.model.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {
}
