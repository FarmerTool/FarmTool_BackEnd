package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.EntryDTO;
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
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);

        // TODO: 10/25/2021 If i find a better solution for mapping packageName to EntryDTO CHANGE IT
        modelMapper.typeMap(Entry.class, EntryDTO.class)
                .addMappings(mapper -> mapper.map(Entry::getPackName, EntryDTO::setPackageName));

        return modelMapper.map(object, classType);
    }
}
