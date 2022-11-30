package com.example.magicbasebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class AddCardRequestDto {

    private String apiId;

    private String name;

    private String oracleText;

    private String rarity;

    private String typeLine;

    private int power;

    private int toughness;

    private int convertedManaCost;

    private String setName;

    private double euroPrice;

    private String imageUrl;

    private int quantity;

    private Long containerId;

}
