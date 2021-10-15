package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserFruitDTO;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final Class<UserFruitDTO> classToConvertTo = UserFruitDTO.class;

    public List<UserFruitDTO> getAllFruitsByUserId(UUID userId){
        return fruitRepository.findByUser_userId(userId)
                .stream()
                .map(fruit -> modelMappingService.convertModelToDTO(fruit,classToConvertTo))
                .collect(Collectors.toList());
    }

    public UserFruitDTO getFruitByFruitId(UUID fruitId){
        return modelMappingService.convertModelToDTO(fruitRepository.getById(fruitId),classToConvertTo);
    }

    public UserFruitDTO addFruitByUserId(UUID userId, Fruit fruit){
        fruit.setUser(userService.fetchUserById(userId));
        return modelMappingService.convertModelToDTO(fruitRepository.save(fruit),classToConvertTo);
    }

    public UserFruitDTO editFruitByFruitId(UUID fruitId,Fruit fruitRequest) {
        Fruit editedFruit = fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruit.setFruitName(fruitRequest.getFruitName());
                    return fruitRepository.save(fruit);
                }).get();
        return modelMappingService.convertModelToDTO(editedFruit,classToConvertTo);
    }

    public UserFruitDTO deleteFruitByFruitId(UUID fruitId) {
        Fruit deletedFruit = fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruitRepository.delete(fruit);
                    return fruit;
                }).get();
        return modelMappingService.convertModelToDTO(deletedFruit,classToConvertTo);
    }
}
