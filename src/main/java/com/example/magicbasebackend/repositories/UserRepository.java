package com.example.magicbasebackend.repositories;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
