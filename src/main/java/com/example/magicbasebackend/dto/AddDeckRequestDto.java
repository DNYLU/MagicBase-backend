package com.example.magicbasebackend.dto;


import com.example.magicbasebackend.enums.DeckFormatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddDeckRequestDto {
    private String name;
    private String description;
    private Long userId;
    private DeckFormatType formatType;

}
