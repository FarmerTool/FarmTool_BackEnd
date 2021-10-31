package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.EntryDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.EntryRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.PackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EntryService.class, VarietyService.class, PackageService.class, ModelMappingService.class})
@ExtendWith(SpringExtension.class)
public class EntryServiceTest {

    @Autowired
    private EntryService entryService;
    @MockBean
    private ModelMappingService modelMappingService;
    @MockBean
    private VarietyService varietyService;
    @MockBean
    private EntryRepository entryRepository;
    @MockBean
    private PackageService packageService;

    @Test
    public void testGetAllEntriesByUserIdTestWhenThereAreNoData() {
        when(this.entryRepository.findByUserUserId(any())).thenReturn(new ArrayList<>());
        assertTrue(this.entryService.getAllEntriesByUserId(any()).isEmpty());
        verify(this.entryRepository).findByUserUserId(any());
    }

    @Test
    public void testGetAllEntriesByUserIdTestWhenThereAreData() {
        Entry entry = new Entry();
        List<Entry> list = Collections.singletonList(entry);

        when(this.entryRepository.findByUserUserId(any())).thenReturn(list);
        assertEquals(1, this.entryService.getAllEntriesByUserId(any()).size());
        verify(this.entryRepository).findByUserUserId(any());
    }

    @Test
    public void testGetAllEntriesByFruitId() {
        Entry entry = new Entry();
        List<Entry> list = Collections.singletonList(entry);

        when(this.entryRepository.findByFruit_FruitId(any())).thenReturn(list);
        assertEquals(1, this.entryService.getAllEntriesByFruitId(any()).size());
        verify(this.entryRepository).findByFruit_FruitId(any());
    }

    @Test
    public void testGetAllEntriesByVarietyId() {
        Entry entry = new Entry();
        List<Entry> list = Collections.singletonList(entry);

        when(this.entryRepository.findByVarietyVarietyId(any())).thenReturn(list);
        assertEquals(1, this.entryService.getAllEntriesByVarietyId(any()).size());
        verify(this.entryRepository).findByVarietyVarietyId(any());
    }

    @Test
    public void testAddEntryByVarietyIdAndPackageId() {
        Variety variety = new Variety();
        variety.setFruit(new Fruit());
        variety.setUser(new User());

        Entry entry = new Entry();
        EntryDTO entryDTO = new EntryDTO();

        when(this.varietyService.fetchVarietyByVarietyId(any())).thenReturn(variety);
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(entryDTO);
        assertSame(this.entryService.addEntryByVarietyIdAndPackageId(UUID.randomUUID(), UUID.randomUUID(), entry), entryDTO);

    }



}
