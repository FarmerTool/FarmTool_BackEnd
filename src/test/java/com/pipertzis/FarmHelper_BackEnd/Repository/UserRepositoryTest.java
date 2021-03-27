package com.pipertzis.FarmHelper_BackEnd.Repository;

import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void saveUserTest(){
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid
        ,"name"
        ,"surname"
        ,"email"
        ,"12345"
        ,"12345");

        User testUser = repository.save(user);
        assertEquals(user.getName(), testUser.getName());

    }
}
