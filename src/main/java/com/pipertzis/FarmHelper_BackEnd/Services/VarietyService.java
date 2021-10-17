package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.VarietyFruitUserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VarietyService {

    @Autowired
    private VarietyRepository varietyRepository;
    @Autowired
    private FruitService fruitService;
    @Autowired
    private ModelMappingService modelMappingService;
    private final Class<VarietyFruitUserDTO> classToConvertTo = VarietyFruitUserDTO.class;

    public List<VarietyFruitUserDTO> getAllVarietiesByUserId(UUID userId) {
        return varietyRepository.findByUser_userId(userId)
                .stream()
                .map(variety -> modelMappingService.convertModelToDTO(variety, classToConvertTo))
                .collect(Collectors.toList());
    }

    public List<VarietyFruitUserDTO> getAllVarietiesByFruitId(UUID fruitId) {
        return varietyRepository.findByFruit_fruitId(fruitId)
                .stream()
                .map(variety -> modelMappingService.convertModelToDTO(variety, classToConvertTo))
                .collect(Collectors.toList());
    }

    public VarietyFruitUserDTO getVarietyByVarietyId(UUID varietyId) {
        return modelMappingService.convertModelToDTO(varietyRepository.getById(varietyId), classToConvertTo);
    }

    public VarietyFruitUserDTO addVarietyByFruitId(UUID fruitId, Variety varietyRequest) {
        Variety savedVariety;

        Fruit fruit = fruitService.fetchFruitById(fruitId);

        varietyRequest.setFruit(fruit);
        varietyRequest.setUser(fruit.getUser());
        savedVariety = varietyRepository.save(varietyRequest);

        return modelMappingService.convertModelToDTO(savedVariety, classToConvertTo);
    }

    public VarietyFruitUserDTO editVarietyNameByVarietyId(UUID varietyId, Variety varietyRequest) {
        Variety editedVariety = varietyRepository.findById(varietyId)
                .map(variety -> {
                    variety.setVarietyName(varietyRequest.getVarietyName());
                    return varietyRepository.save(variety);
                }).get();
        return modelMappingService.convertModelToDTO(editedVariety, classToConvertTo);
    }

    public VarietyFruitUserDTO deleteVarietyByVarietyId(UUID varietyId) {
        Variety deletedVariety = varietyRepository.findById(varietyId)
                .map(variety -> {
                    varietyRepository.delete(variety);
                    return variety;
                }).get();
        return modelMappingService.convertModelToDTO(deletedVariety, classToConvertTo);
    }


}
