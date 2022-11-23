package com.example.magicbasebackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long collectionId;

    /*@OneToMany()
    @JoinTable(name = "collection_line_card")
    private Collections collections;*/

    @Column(name = "collection_type")
    private String collectionType;

    @Column(name = "collection_name")
    private String collectionName;

    @Column(name = "collection_description")
    private String collectionDescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;
}
