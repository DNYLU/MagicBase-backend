package com.example.magicbasebackend.model;

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

    /*@Column(name = "collection_line_card_collection_id")
    private Collection collection;

     */

    @ManyToOne
    private Card card;



}
