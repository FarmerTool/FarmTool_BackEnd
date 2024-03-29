package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.FruitDTO;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FruitService {
    @Autowired
    private FruitRepository fruitRepository;
    @Autowired
    private ModelMappingService modelMappingService;
    @Autowired
    private UserService userService;

    private final Class<FruitDTO> classToConvertTo = FruitDTO.class;

    public List<FruitDTO> getAllFruitsByUserId(UUID userId) {
        return fruitRepository.findByUser_userId(userId)
                .stream()
                .map(fruit -> modelMappingService.convertModelToDTO(fruit, classToConvertTo))
                .collect(Collectors.toList());
    }

    public FruitDTO getFruitByFruitId(UUID fruitId) {
        return modelMappingService.convertModelToDTO(fruitRepository.getById(fruitId), classToConvertTo);
    }

    public FruitDTO addFruitByUserId(UUID userId, Fruit fruit) {
        fruit.setUser(userService.fetchUserById(userId));
        return modelMappingService.convertModelToDTO(fruitRepository.save(fruit), classToConvertTo);
    }

    public FruitDTO editFruitByFruitId(UUID fruitId, Fruit fruitRequest) {
        Fruit editedFruit = fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruit.setFruitName(fruitRequest.getFruitName());
                    return fruitRepository.save(fruit);
                }).orElseThrow(EntityNotFoundException::new);
        return modelMappingService.convertModelToDTO(editedFruit, classToConvertTo);
    }

    public FruitDTO deleteFruitByFruitId(UUID fruitId) {
        Fruit deletedFruit = fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruitRepository.delete(fruit);
                    return fruit;
                }).orElseThrow(EntityNotFoundException::new);
        return modelMappingService.convertModelToDTO(deletedFruit, classToConvertTo);
    }

    public Fruit fetchFruitById(UUID fruitId) {
        return fruitRepository.getById(fruitId);
    }
}
