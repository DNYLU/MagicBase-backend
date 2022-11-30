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
public class DeckLineCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_line_card_id")
    private Long id;

    @Column(name = "deck_line_card_quantity")
    private int quantity;

    @ManyToOne
    @JsonBackReference
    private Deck deck;

    @ManyToOne
    private Card card;


}
