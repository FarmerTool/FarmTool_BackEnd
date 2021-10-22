package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.VarietyFruitUserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Services.VarietyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pipertzis.FarmHelper_BackEnd.AbstractClass.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = VarietyController.class)
@ExtendWith(SpringExtension.class)

public class VarietyControllerTest {
    @Autowired
    private VarietyController varietyController;

    @MockBean
    private VarietyService varietyService;


    @Test
    public void testGetVarietyById() throws Exception {
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        when(this.varietyService.getVarietyByVarietyId(any())).thenReturn(varietyFruitUserDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/variety/{varietyId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyFruitUserDTO)))
                .andDo(print());
        verify(this.varietyService).getVarietyByVarietyId(any());
    }

    @Test
    public void testGetAllVarietiesByFruitId() throws Exception {
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        varietyFruitUserDTO.setVarietyId(UUID.randomUUID());
        varietyFruitUserDTO.setFruitName("testFruitName");
        varietyFruitUserDTO.setUsername("testUsername");
        varietyFruitUserDTO.setVarietyName("testVarietyName");

        List<VarietyFruitUserDTO> list = new ArrayList<>();
        list.add(varietyFruitUserDTO);

        when(this.varietyService.getAllVarietiesByFruitId(any())).thenReturn(list);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/variety/all/{fruitId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].varietyName").value("testVarietyName"))
                .andDo(print());

        verify(this.varietyService).getAllVarietiesByFruitId(any());
    }

    @Test
    public void testGetAllVarietiesByUserId() throws Exception {
        VarietyFruitUserDTO varietyFruitUserDTO  = new VarietyFruitUserDTO();
        varietyFruitUserDTO.setVarietyId(UUID.randomUUID());
        varietyFruitUserDTO.setUsername("testUsername");
        varietyFruitUserDTO.setVarietyName("testVarietyName");
        varietyFruitUserDTO.setFruitName("testFruitName");
        List<VarietyFruitUserDTO> list = new ArrayList<>();
        list.add(varietyFruitUserDTO);

        when(this.varietyService.getAllVarietiesByUserId(any())).thenReturn(list);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/variety/user/{userId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].varietyName").value("testVarietyName"))
                .andDo(print());

        verify(this.varietyService).getAllVarietiesByUserId(any());
    }
    @Test
    public void testAddVarietyByFruitId() throws Exception {
        Variety variety = new Variety();
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        varietyFruitUserDTO.setFruitName("testFruitName");

        when(this.varietyService.addVarietyByFruitId(any(),any())).thenReturn(varietyFruitUserDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/variety/add/{fruitId}",UUID.randomUUID())
                .content(asJsonString(variety))
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(varietyFruitUserDTO)))
                .andExpect(jsonPath("$.fruitName").value("testFruitName"))
                .andDo(print());
        verify(this.varietyService).addVarietyByFruitId(any(),any());
    }

    @Test
    public void testEditVarietyNameById() throws Exception {
        Variety variety = new Variety();
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();

        when(this.varietyService.editVarietyNameByVarietyId(any(),any())).thenReturn(varietyFruitUserDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.put("/variety/edit/{varietyId}",UUID.randomUUID())
                                                                        .content(asJsonString(variety))
                                                                        .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyFruitUserDTO)))
                .andDo(print());

        verify(this.varietyService).editVarietyNameByVarietyId(any(),any());

    }

    @Test
    public void testDeleteVarietyByVarietyId() throws Exception {
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();

        when(this.varietyService.deleteVarietyByVarietyId(any())).thenReturn(varietyFruitUserDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.delete("/variety/delete/{varietyId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyFruitUserDTO)))
                .andDo(print());

        verify(this.varietyService).deleteVarietyByVarietyId(any());
    }


}
