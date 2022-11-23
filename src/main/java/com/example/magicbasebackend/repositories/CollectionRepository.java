package com.example.magicbasebackend.repositories;

import com.example.magicbasebackend.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    public List<Collection> findAllByCollectionId(Long id); // Show deck/cards?
}
