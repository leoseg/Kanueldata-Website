package com.example.kanuledatawebsite.daotests;
import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("testuser@gmail.com");
        user.setPassword("testuser2022");
        user.setUserName("testuser");


        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getUserName()).isEqualTo(existUser.getUserName());
    }

}
