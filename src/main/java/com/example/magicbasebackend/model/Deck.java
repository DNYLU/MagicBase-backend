package com.example.magicbasebackend.model;

import com.example.magicbasebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "deck")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id")
    private Long id;

    @ManyToMany()
    @JsonBackReference
    @JoinTable(name = "users_has_deck",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


    @Column(name = "deck_name")
    private String name;

    @Column(name = "deck_description")
    private String description;


    @Column(name = "deck_format_type")
    private String formatType;

    @Column(name = "deck_public")
    private boolean isPublic;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    private User owner;

}