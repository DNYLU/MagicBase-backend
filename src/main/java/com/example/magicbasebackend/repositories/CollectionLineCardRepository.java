package com.example.magicbasebackend.repositories;


import com.example.magicbasebackend.model.CollectionLineCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CollectionLineCardRepository extends PagingAndSortingRepository<CollectionLineCard, Long> {
    List<CollectionLineCard> findByCollectionId(Long collectionId);


}
