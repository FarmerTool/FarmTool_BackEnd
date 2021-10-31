package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.VarietyDTO;
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
        VarietyDTO varietyDTO = new VarietyDTO();
        when(this.varietyService.getVarietyByVarietyId(any())).thenReturn(varietyDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/variety/{varietyId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyDTO)))
                .andDo(print());
        verify(this.varietyService).getVarietyByVarietyId(any());
    }

    @Test
    public void testGetAllVarietiesByFruitId() throws Exception {
        VarietyDTO varietyDTO = new VarietyDTO();
        varietyDTO.setVarietyId(UUID.randomUUID());
        varietyDTO.setFruitName("testFruitName");
        varietyDTO.setUsername("testUsername");
        varietyDTO.setVarietyName("testVarietyName");

        List<VarietyDTO> list = new ArrayList<>();
        list.add(varietyDTO);

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
        VarietyDTO varietyDTO = new VarietyDTO();
        varietyDTO.setVarietyId(UUID.randomUUID());
        varietyDTO.setUsername("testUsername");
        varietyDTO.setVarietyName("testVarietyName");
        varietyDTO.setFruitName("testFruitName");
        List<VarietyDTO> list = new ArrayList<>();
        list.add(varietyDTO);

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
        VarietyDTO varietyDTO = new VarietyDTO();
        varietyDTO.setFruitName("testFruitName");

        when(this.varietyService.addVarietyByFruitId(any(),any())).thenReturn(varietyDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/variety/add/{fruitId}",UUID.randomUUID())
                .content(asJsonString(variety))
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(varietyDTO)))
                .andExpect(jsonPath("$.fruitName").value("testFruitName"))
                .andDo(print());
        verify(this.varietyService).addVarietyByFruitId(any(),any());
    }

    @Test
    public void testEditVarietyNameById() throws Exception {
        Variety variety = new Variety();
        VarietyDTO varietyDTO = new VarietyDTO();

        when(this.varietyService.editVarietyNameByVarietyId(any(),any())).thenReturn(varietyDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.put("/variety/edit/{varietyId}",UUID.randomUUID())
                                                                        .content(asJsonString(variety))
                                                                        .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyDTO)))
                .andDo(print());

        verify(this.varietyService).editVarietyNameByVarietyId(any(),any());

    }

    @Test
    public void testDeleteVarietyByVarietyId() throws Exception {
        VarietyDTO varietyDTO = new VarietyDTO();

        when(this.varietyService.deleteVarietyByVarietyId(any())).thenReturn(varietyDTO);

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.delete("/variety/delete/{varietyId}",UUID.randomUUID());

        MockMvcBuilders.standaloneSetup(this.varietyController)
                .build()
                .perform(getResult)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(varietyDTO)))
                .andDo(print());

        verify(this.varietyService).deleteVarietyByVarietyId(any());
    }


}
