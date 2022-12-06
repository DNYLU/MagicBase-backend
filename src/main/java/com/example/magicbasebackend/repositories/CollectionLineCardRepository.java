package com.example.magicbasebackend.repositories;


import com.example.magicbasebackend.model.CollectionLineCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollectionLineCardRepository extends PagingAndSortingRepository<CollectionLineCard, Long> {
    Page<CollectionLineCard> findByCollectionId(Long collectionId, Pageable pageable);

}
