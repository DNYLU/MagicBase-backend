package com.example.magicbasebackend.repositories;

import com.example.magicbasebackend.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findByUsersId(Long userId);
}
