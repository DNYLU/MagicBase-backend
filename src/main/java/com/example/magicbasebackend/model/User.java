package com.example.magicbasebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_username")
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Deck> decksOwned;

    @OneToMany(mappedBy = "owner")
    private List<Collection> collectionsOwned;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_has_collection",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id")
    )
    private Set<Collection> collections = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_has_deck",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "deck_id")
    )
    private Set<Deck> decks;


    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
