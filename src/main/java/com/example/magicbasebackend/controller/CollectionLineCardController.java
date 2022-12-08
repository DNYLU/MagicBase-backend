package com.example.magicbasebackend.controller;


import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.CollectionLineCard;
import com.example.magicbasebackend.services.CollectionLineCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/clc")
public class CollectionLineCardController {

    private CollectionLineCardService collectionLineCardService;

    public CollectionLineCardController(CollectionLineCardService collectionLineCardService) {
        this.collectionLineCardService = collectionLineCardService;
    }

    @PostMapping()
    public ResponseEntity<CollectionLineCard> add(@RequestBody AddCardRequestDto addCardRequest) {
        CollectionLineCard clc = collectionLineCardService.addCollectionCards(addCardRequest);

        // skal lige ændres til at returnere det rigtige
        return new ResponseEntity<>(clc, HttpStatus.OK);
    }

    @GetMapping("/collection/{id}")
        public ResponseEntity<Page<CollectionLineCard>> all(@PathVariable("id") Long collectionId,
                                                            @RequestParam(defaultValue = "0") int pageNo,
                                                            @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println(collectionId);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        return new ResponseEntity<>(collectionLineCardService.getByCollectionIdPaged(collectionId, pageRequest),
                                    HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<CollectionLineCard> update(@RequestBody CollectionLineCard collectionLineCard){
        return ResponseEntity.ok().body(collectionLineCardService.update(collectionLineCard));
    }
}

