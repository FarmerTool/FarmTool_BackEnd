package com.pipertzis.FarmHelper_BackEnd.Repository;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.VarietyRepository;
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
public class VarietyRepositoryTest {
    @Autowired
    VarietyRepository varietyRepository;

    @Test
    public void saveVarietyTest(){
        User user = new User(UUID.fromString("0ad1fd93-ffc7-4537-9e65-98f9a90fba46"),"test","test","test","test","test");
        Fruit fruit = new Fruit(UUID.fromString("bd6e945d-18e6-4123-88a7-b0dea8b40fd0"),"testname", user );
        Variety variety = new Variety(null,"testName",fruit,user);
        Variety testVariety = varietyRepository.save(variety);
        assertEquals(testVariety.getFruit().getFruitId(),fruit.getFruitId());
    }
}
