package com.example.magicbasebackend.model;

import com.example.magicbasebackend.enums.DeckFormatType;
import com.example.magicbasebackend.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "deck")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id")
    private Long id;

    @ManyToMany(mappedBy = "decks")
    @JsonBackReference(value = "users")
    private Set<User> users = new HashSet<>();


    @Column(name = "deck_name")
    private String name;

    @Column(name = "deck_description")
    private String description;


    @Column(name = "deck_format_type")
    private DeckFormatType formatType;

    @Column(name = "deck_public")
    private boolean isPublic;

    @ManyToOne
    @JsonBackReference(value = "owner")
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DeckLineCard> deckLineCards;

    public void removeUser(User user){
        this.users.remove(user);
        user.getDecks().remove(this);
    }

    public void addUser(User user){
        this.users.add(user);
        user.getDecks().add(this);
    }

}