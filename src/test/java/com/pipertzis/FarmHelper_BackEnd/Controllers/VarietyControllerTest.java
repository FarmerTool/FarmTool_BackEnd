package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.AbstractTest;
import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class VarietyControllerTest extends AbstractTest {
    private String uri = "/variety/";
    private final User testUser = new User(null,"test","test","email","test","test");
    private final Fruit testFruit = new Fruit(null,"testName",null);
    private final Variety testVariety = new Variety(null,"testName",null,null);
    private final String userUuid = "0ad1fd93-ffc7-4537-9e65-98f9a90fba46";
    private final String fruitUuid = "bd6e945d-18e6-4123-88a7-b0dea8b40fd0";
    private final String varietyUuid = "313a0f49-77d6-4cdb-ad99-e20906d1762c";

    @Test
    void getAllVarietyById() throws Exception{
        uri += varietyUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Variety checkVariety = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Variety.class);
        assertEquals(checkVariety.getVarietyId(),UUID.fromString(varietyUuid));
    }

    @Test
    void getAllVarietiesByFruitId() throws Exception {
        uri += "all/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Variety[] varieties = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Variety[].class);
        assertTrue(varieties.length > 0);
        assertEquals(Arrays.stream(varieties).findAny().get().getFruit().getFruitId(),UUID.fromString(fruitUuid));
    }

    @Test
    void getAllVarietiesByUserId() throws Exception{
        uri += "user/" + userUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Variety[] varieties = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Variety[].class);
        assertTrue(varieties.length > 0);
        assertEquals(Arrays.stream(varieties).findAny().get().getUser().getUserId(),UUID.fromString(userUuid));
    }

    @Test
    void addVarietyByFruitId() throws Exception {
        uri += "add/" + fruitUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testVariety))
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        Variety checkVariety = super.mapFromJson(mvcResult.getResponse().getContentAsString(),Variety.class);
        assertEquals(checkVariety.getFruit().getFruitId(),UUID.fromString(fruitUuid));
    }

    @Test
    void addVarietyByWrongFruitId() throws Exception{
        uri += "add/" + UUID.randomUUID();
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testVariety))
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void editVarietyNameById() throws Exception {
        uri += "edit/" + varietyUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testVariety))
        ).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void editVarietyNameByWrongId() throws Exception{
        uri += "edit/" + UUID.randomUUID();
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testVariety))
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void deleteVarietyByVarietyId() throws Exception {
        uri += "delete/" + varietyUuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void deleteVarietyByWrongVarietyId() throws Exception {
        uri += "delete/" + UUID.randomUUID();
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}