package com.example.magicbasebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CollectionLineCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_line_card_id")
    private Long id;

    @Column(name = "collection_line_card_quantity")
    private int quantity;

    @ManyToOne
    @JsonBackReference
    private Collection collection;

    @ManyToOne
    private Card card;

    public CollectionLineCard updateFrom(CollectionLineCard collectionLineCard){
        if(this.quantity != collectionLineCard.getQuantity()){
            this.quantity = collectionLineCard.getQuantity();
        }
            return this;
        }
}
