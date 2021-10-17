package com.pipertzis.FarmHelper_BackEnd.Services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMappingService {

    @Autowired
    private ModelMapper modelMapper;

    public <T> T convertModelToDTO(Object object, Class<T> classType) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(object, classType);
    }
}
