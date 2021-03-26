package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.AbstractTest;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

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
public class UserControllerTest extends AbstractTest {
    private String uri = "/user/";
    private final User testUser = new User(null,"test","test","email","test","test");
    private final String uuid = "545a8848-0ef7-4bbf-b1a6-f84d730209ce";

    @Test
    public void getAllUsersTest() throws Exception {
        uri += "all";
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        User[] users = super.mapFromJson(mvcResult.getResponse().getContentAsString(),User[].class);
        assertTrue(users.length > 0 );
    }

    @Test
    public void postUserTest()throws Exception{
        uri += "add";
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri).content(super.mapToJson(testUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print()).andReturn();
        User tempUser = super.mapFromJson(mvcResult.getResponse().getContentAsString(),User.class);
        assertEquals(testUser.getEmail(),tempUser.getEmail());

    }

    @Test
    public void putUserTest()throws Exception{
        uri += "edit/" + uuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri).content(super.mapToJson(testUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print()).andReturn();
        User tempUser = super.mapFromJson(mvcResult.getResponse().getContentAsString(),User.class);
        assertEquals(testUser.getEmail(),"email");
    }

    @Test
    public void deleteUserTest() throws Exception{
        uri += "delete/" + uuid;
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print()).andReturn();
    }

}
