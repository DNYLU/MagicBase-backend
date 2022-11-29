package com.example.magicbasebackend.repositories;

import com.example.magicbasebackend.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {

    Card findByCardApiId(String apiId);

}
