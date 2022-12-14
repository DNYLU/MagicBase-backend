package com.example.magicbasebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "card_id")
    private Long id;

    @Column(name = "card_api_id", unique = true)
    private String apiId;

    @Column(name = "card_name")
    private String name;


    @Column(name = "card_oracle_text", length = 700)
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

    @Column(name = "card_image_url_transform")
    private String imageUrlTransform;

    @ManyToMany
    @JoinTable(
            name = "card_has_color",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<Color> colors;

    @JsonBackReference
    @OneToMany(mappedBy = "card")
    private List<CollectionLineCard> collectionLineCards = new ArrayList<>();
}
