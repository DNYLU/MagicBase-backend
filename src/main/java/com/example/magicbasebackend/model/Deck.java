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

    @ManyToMany(mappedBy = "decks")
    @JsonBackReference(value = "users")
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
    @JsonBackReference(value = "owner")
    @JoinColumn(name = "owner_id")
    private User owner;
    public void removeUser(User user){
        this.users.remove(user);
        user.getDecks().remove(this);
    }

}