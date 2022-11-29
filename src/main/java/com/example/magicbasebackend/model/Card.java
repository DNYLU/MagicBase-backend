package com.example.magicbasebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;


    @Column(name = "card_api_id", unique = true)
    private String cardApiId;


    @Column(name = "card_name")
    private String name;

    @Column(name = "card_oracle_text")
    private String oracleText;

    @Column(name = "card_rarity")
    private String rarity;

    @Column(name = "card_type_line")
    private String typeLine;

    @Column(name = "card_power")
    private int power;

    @Column(name = "card_toughness")
    private int toughness;

    @Column(name = "card_cmc")
    private int convertedManaCost;

    @Column(name = "card_set_name")
    private String setName;

    @Column(name = "card_euro_price")
    private double euroPrice;

    @Column(name = "card_image_url")
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "card_has_color",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<Color> colors;


    @OneToMany(mappedBy = "card")
    private List<CollectionLineCard> collectionLineCards = new ArrayList<>();


}