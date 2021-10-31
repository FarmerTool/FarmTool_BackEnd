package com.pipertzis.FarmHelper_BackEnd.Controllers;

import static com.pipertzis.FarmHelper_BackEnd.AbstractClass.asJsonString;
import static org.mockito.Mockito.*;

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.EntryDTO;
import com.pipertzis.FarmHelper_BackEnd.Services.EntryService;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

@ContextConfiguration(classes = {EntryController.class})
@ExtendWith(SpringExtension.class)
class EntryControllerTest {
    @Autowired
    private EntryController entryController;

    @MockBean
    private EntryService entryService;

    @Test
    void testGetAllEntriesByUserIdWhenThereAreNoData() throws Exception {
        when(this.entryService.getAllEntriesByUserId(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/entry/user/{userId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));

        verify(this.entryService).getAllEntriesByUserId(any());
    }

    @Test
    void testGetAllEntriesByUserIdWhenThereAreData() throws Exception {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        List<EntryDTO> list = Collections.singletonList(entryDTO);

        when(this.entryService.getAllEntriesByUserId(any())).thenReturn(list);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/entry/user/{userId}", UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].amount").value(12345));
        verify(this.entryService).getAllEntriesByUserId(any());
    }

    @Test
    void testGetAllEntriesByFruitId() throws Exception {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        List<EntryDTO> list = Collections.singletonList(entryDTO);

        when(this.entryService.getAllEntriesByFruitId(any())).thenReturn(list);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/entry/fruit/{fruitId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].amount").value(12345));

        verify(this.entryService).getAllEntriesByFruitId(any());
    }

    @Test
    void testGetAllEntriesByVarietyId() throws Exception {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        List<EntryDTO> list = Collections.singletonList(entryDTO);

        when(this.entryService.getAllEntriesByVarietyId(any())).thenReturn(list);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/entry/variety/{varietyId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].amount").value(12345));

        verify(this.entryService).getAllEntriesByVarietyId(any());
    }

    @Test
    void testAddEntryByVarietyIdAndPackageId() throws Exception {
        UUID id = UUID.randomUUID();
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        entryDTO.setEntryId(id);
        entryDTO.setVarietyName("testVarietyName");
        entryDTO.setPackageName("testPackage");
        entryDTO.setPrice(123);
        entryDTO.setUsername("testUsername");
        entryDTO.setWeight(12);
        entryDTO.setFruitName("testFruitName");

        when(this.entryService.addEntryByVarietyIdAndPackageId(any(),any(),any())).thenReturn(entryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/entry/add/{varietyId}/{packageId}",UUID.randomUUID(), UUID.randomUUID())
                .content(asJsonString(entryDTO))
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(asJsonString(entryDTO)));
        verify(this.entryService).addEntryByVarietyIdAndPackageId(any(),any(),any());
    }

    @Test
    void testEditEntryByEntryId() throws Exception {
        UUID id = UUID.randomUUID();
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        entryDTO.setEntryId(id);
        entryDTO.setVarietyName("testVarietyName");
        entryDTO.setPackageName("testPackage");
        entryDTO.setPrice(123);
        entryDTO.setUsername("testUsername");
        entryDTO.setWeight(12);
        entryDTO.setFruitName("testFruitName");

        when(this.entryService.editEntryByEntryId(any(),any())).thenReturn(entryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/entry/edit/{entryId}",UUID.randomUUID())
                .content(asJsonString(entryDTO))
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(asJsonString(entryDTO)));
        verify(this.entryService).editEntryByEntryId(any(),any());
    }

    @Test
    void testDeleteEntryByEntryId() throws Exception {
        UUID id = UUID.randomUUID();
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setAmount(12345);
        entryDTO.setEntryId(id);
        entryDTO.setVarietyName("testVarietyName");
        entryDTO.setPackageName("testPackage");
        entryDTO.setPrice(123);
        entryDTO.setUsername("testUsername");
        entryDTO.setWeight(12);
        entryDTO.setFruitName("testFruitName");
        when(this.entryService.deleteEntryByEntryId(any())).thenReturn(entryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/entry/delete/{entryId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.entryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"entryId\":\"" + id + "\",\"amount\":12345,\"price\":123.0,\"weight\":12.0,\"date\":null,\"username\":\"testUsername\",\"fruitName\":\"testFruitName\","
                                        + "\"varietyName\":\"testVarietyName\",\"packageName\":\"testPackage\"}"));

        verify(this.entryService).deleteEntryByEntryId(any());

    }
}

