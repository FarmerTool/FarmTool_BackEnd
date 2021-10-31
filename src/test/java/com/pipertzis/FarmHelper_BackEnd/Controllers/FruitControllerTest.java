package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.FruitDTO;
import com.pipertzis.FarmHelper_BackEnd.Services.FruitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pipertzis.FarmHelper_BackEnd.AbstractClass.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {FruitController.class})
@ExtendWith(SpringExtension.class)
public class FruitControllerTest {
    @Autowired
    private FruitController fruitController;

    @MockBean
    private FruitService fruitService;

    @Test
    public void testGetAllFruitsByUserId() throws Exception {
        when(this.fruitService.getAllFruitsByUserId(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fruit/user/{userId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"))
                .andDo(print());
    }

    @Test
    public void testGetAllFruitsByUserIdWhenThereIsAFruit() throws Exception {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setFruitId(UUID.randomUUID());
        fruitDTO.setFruitName("testName");
        List<FruitDTO> list = new ArrayList<>();
        list.add(fruitDTO);

        when(this.fruitService.getAllFruitsByUserId(any())).thenReturn(list);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/fruit/user/{userId}", UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].fruitName").value("testName"))
                .andDo(print());

        verify(this.fruitService).getAllFruitsByUserId(any());
    }

    @Test
    public void testGetFruitByFruitId() throws Exception {
        FruitDTO fruitDTO = new FruitDTO();

        when(this.fruitService.getFruitByFruitId(any())).thenReturn(fruitDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/fruit/{fruitId}", UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fruitDTO)))
                .andDo(print());

        verify(this.fruitService).getFruitByFruitId(any());

    }

    @Test
    public void testAddFruitByUserId() throws Exception {
        FruitDTO fruitDTO = new FruitDTO();
        Fruit fruit = new Fruit();

        when(this.fruitService.addFruitByUserId(any(),any())).thenReturn(fruitDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/fruit/add/{userId}",UUID.randomUUID())
                                                                        .content(asJsonString(fruit))
                                                                        .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fruitDTO)))
                .andDo(print());

        verify(this.fruitService).addFruitByUserId(any(),any());
    }

    @Test
    public void testEditFruitByFruitId() throws Exception {
        Fruit fruit = new Fruit();
        FruitDTO fruitDTO = new FruitDTO();

        when(this.fruitService.editFruitByFruitId(any(),any())).thenReturn(fruitDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.put("/fruit/edit/{fruitId}",UUID.randomUUID())
                                                                        .content(asJsonString(fruit))
                                                                        .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fruitDTO)))
                .andDo(print());

        verify(this.fruitService).editFruitByFruitId(any(),any());

    }

    @Test
    public void testDeleteFruitByFruitId() throws Exception {
        FruitDTO fruitDTO = new FruitDTO();

        when(this.fruitService.deleteFruitByFruitId(any())).thenReturn(fruitDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.delete("/fruit/delete/{fruitId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.fruitController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fruitDTO)))
                .andDo(print());

        verify(this.fruitService).deleteFruitByFruitId(any());

    }
}

