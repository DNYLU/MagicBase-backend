package com.example.magicbasebackend.dto;

import com.example.magicbasebackend.enums.CollectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCollectionRequestDto {
    private Long userId;

    private CollectionType type;

    private String name;

    private String description;
}