package com.example.magicbasebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareDeckRequestDto {
    private String username;
    private Long deckId;
}
