package com.example.magicbasebackend;

import com.example.magicbasebackend.model.User;
import com.example.magicbasebackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class MagicBaseBackendApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User user = userRepository.findByUsername("bobsen").get();
        System.out.println(user);



    }

}
