package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.VarietyDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    private final Class<VarietyDTO> classToConvertTo = VarietyDTO.class;

    public List<VarietyDTO> getAllVarietiesByUserId(UUID userId) {
        return varietyRepository.findByUser_userId(userId)
                .stream()
                .map(variety -> modelMappingService.convertModelToDTO(variety, classToConvertTo))
                .collect(Collectors.toList());
    }

    public List<VarietyDTO> getAllVarietiesByFruitId(UUID fruitId) {
        return varietyRepository.findByFruit_fruitId(fruitId)
                .stream()
                .map(variety -> modelMappingService.convertModelToDTO(variety, classToConvertTo))
                .collect(Collectors.toList());
    }

    public VarietyDTO getVarietyByVarietyId(UUID varietyId) {
        return modelMappingService.convertModelToDTO(varietyRepository.getById(varietyId), classToConvertTo);
    }

    public VarietyDTO addVarietyByFruitId(UUID fruitId, Variety varietyRequest) {
        Variety savedVariety;

        Fruit fruit = fruitService.fetchFruitById(fruitId);

        varietyRequest.setFruit(fruit);
        varietyRequest.setUser(fruit.getUser());
        savedVariety = varietyRepository.save(varietyRequest);

        return modelMappingService.convertModelToDTO(savedVariety, classToConvertTo);
    }

    public VarietyDTO editVarietyNameByVarietyId(UUID varietyId, Variety varietyRequest) {
        Variety editedVariety = varietyRepository.findById(varietyId)
                .map(variety -> {
                    variety.setVarietyName(varietyRequest.getVarietyName());
                    return varietyRepository.save(variety);
                }).orElseThrow(EntityNotFoundException::new);
        return modelMappingService.convertModelToDTO(editedVariety, classToConvertTo);
    }

    public VarietyDTO deleteVarietyByVarietyId(UUID varietyId) {
        Variety deletedVariety = varietyRepository.findById(varietyId)
                .map(variety -> {
                    varietyRepository.delete(variety);
                    return variety;
                }).orElseThrow(EntityNotFoundException::new);
        return modelMappingService.convertModelToDTO(deletedVariety, classToConvertTo);
    }

    public Variety fetchVarietyByVarietyId(UUID varietyId){
        return varietyRepository.getById(varietyId);
    }


}
