package com.example.magicbasebackend.model;

import com.example.magicbasebackend.enums.CollectionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<CollectionLineCard> collectionLineCards;

    @Column(name = "collection_type")
    private CollectionType type;

    @Column(name = "collection_name")
    private String name;

    @Column(name = "collection_description")
    private String description;

    @Column(name = "collection_public")
    private boolean isPublic;

    @ManyToOne
    @JsonBackReference(value = "owner")
    @JoinColumn(name = "owner_id")
    private User owner;

    @JsonBackReference
    @ManyToMany(mappedBy = "collections")
    private Set<User> users = new HashSet<>();

    public void removeUser(User user) {
        this.users.remove(user);
        user.getCollections().remove(this);
    }
    public void addUser(User user) {
        this.users.add(user);
        user.getCollections().add(this);
    }

}
