package com.pipertzis.FarmHelper_BackEnd.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.InheritingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ModelMapper.class, ModelMappingService.class})
@ExtendWith(SpringExtension.class)
public class ModelMappingServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ModelMappingService modelMappingService;

    @Test
    public void testConvertModelToDTO() {
        when(this.modelMapper.map(any(), any())).thenReturn("42");
        when(this.modelMapper.getConfiguration()).thenReturn(new InheritingConfiguration());
        assertEquals("42", this.modelMappingService.convertModelToDTO("Object", Object.class));
        verify(this.modelMapper).getConfiguration();
        verify(this.modelMapper).map(any(), any());
    }
}

