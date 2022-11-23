package com.example.magicbasebackend.repositories;


import com.example.magicbasebackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
