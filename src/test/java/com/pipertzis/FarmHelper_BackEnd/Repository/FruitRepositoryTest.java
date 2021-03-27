package com.pipertzis.FarmHelper_BackEnd.Repository;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class FruitRepositoryTest {
    @Autowired
    FruitRepository repository;

    @Test
    public void saveFruitTest(){
        Fruit fruit = new Fruit(UUID.randomUUID(),"testname",new User(UUID.fromString("0ad1fd93-ffc7-4537-9e65-98f9a90fba46"),"test","test","test","test","test"));
        Fruit testFruit = repository.save(fruit);
        assertEquals(fruit.getFruitName(),testFruit.getFruitName());
    }
}
