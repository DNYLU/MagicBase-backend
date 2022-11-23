package com.example.magicbasebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    @OneToMany(mappedBy = "collection")
    private List<CollectionLineCard> collectionLineCards;

    @Column(name = "collection_type")
    private String type;

    @Column(name = "collection_name")
    private String name;

    @Column(name = "collection_description")
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "collections")
    private List<User> users;
}
