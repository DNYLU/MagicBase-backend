package com.example.magicbasebackend.controller;


import com.example.magicbasebackend.dto.AddCardRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/clc")
public class CollectionLineCard {


    @PostMapping()
    public void add(@RequestBody AddCardRequestDto addCardRequest) {
        System.out.println(addCardRequest);

    }

}
