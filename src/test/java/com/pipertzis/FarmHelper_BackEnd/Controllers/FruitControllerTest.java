package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.AbstractTest;
import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class FruitControllerTest extends AbstractTest {
    private String uri = "/fruits/";
    private final User testUser = new User(null,"test","test","email","test","test");
    private Fruit testFruit = new Fruit(null,"testName",null);
    private final String userUuid = "0ad1fd93-ffc7-4537-9e65-98f9a90fba46";
    private final String fruitUuid = "63dd2394-c384-478e-b86e-58d49dc7bc72";



    @Test
    public void getFruitByUserIdTest() throws Exception{
        uri += "user/" + userUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Fruit[] fruits = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Fruit[].class);
        assertTrue(fruits.length > 0);
        assertEquals(Arrays.stream(fruits).findAny().get().getUser().getUserId(), UUID.fromString(userUuid));
    }

    @Test
    public void getFruitByFruitIdTest() throws Exception{
        uri += "fruit/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Fruit fruit = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Fruit.class);
        assertEquals(fruit.getFruitName(),"portokalia");
    }

    @Test
    public void postFruitTest() throws Exception{
        uri += "add/" + userUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testFruit))
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Fruit fruit = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Fruit.class);
        assertEquals(fruit.getFruitName(),"testname");
    }

    @Test
    public void postFruitByUserIdWrongId() throws Exception{
        uri += "add/" + UUID.randomUUID().toString();
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testFruit))
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void postFruitByUserIdSameFruitName() throws Exception{
        uri += "add/" + userUuid;
        testFruit.setFruitName("portokalia");
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testFruit))
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void putFruitTest() throws Exception{
        Fruit tempFruit = new Fruit(null,"editFruitName",null);
        uri += "edit/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(tempFruit))
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Fruit checkFruit = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Fruit.class);
        assertEquals(tempFruit.getFruitName(),checkFruit.getFruitName());
    }

    @Test
    public void putFruitSameFruitNameTest() throws Exception{
        Fruit tempFruit = new Fruit(null,"aktinidia",null);
        uri += "edit/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(tempFruit))
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }


    @Test
    public void deleteFruitTest() throws Exception{
        uri += "delete/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void deleteFruitWrongFruitId() throws Exception{
        uri += "delete/" + UUID.randomUUID();
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }



}
