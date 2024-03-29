package com.pipertzis.FarmHelper_BackEnd.Services;


import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.PackageDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private ModelMappingService modelMappingService;
    private final Class<PackageDTO> classToConvertTo = PackageDTO.class;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private FruitService fruitService;

    public List<PackageDTO> getAllPackages() {
        return packageRepository.findAll()
                .stream()
                .map(pack -> modelMappingService.convertModelToDTO(pack, classToConvertTo))
                .collect(Collectors.toList());
    }

    public List<PackageDTO> getAllPackagesByFruitId(UUID fruitId) {
        return packageRepository.findByFruit_FruitId(fruitId)
                .stream().map(fruit -> modelMappingService.convertModelToDTO(fruit, classToConvertTo))
                .collect(Collectors.toList());
    }

    public PackageDTO addPackageByFruitId(UUID fruitId, Package pack) {
        Fruit fruit = fruitService.fetchFruitById(fruitId);
        User user = fruit.getUser();
        pack.setFruit(fruit);
        pack.setUser(user);
        pack.setFruitName(fruit.getFruitName());
        return modelMappingService.convertModelToDTO(packageRepository.save(pack), classToConvertTo);
    }

    public PackageDTO editPackageByPackageId(UUID packageId, Package packageRequest) {
        Package editedPackage = packageRepository.findById(packageId)
                .map(pack -> {
                    pack.setPackageName(packageRequest.getPackageName());
                    return pack;
                }).orElseThrow(EntityNotFoundException::new);
        return modelMappingService.convertModelToDTO(packageRepository.save(editedPackage), classToConvertTo);
    }

    public Package fetchPackageByPackageId(UUID packageId){
        return packageRepository.getById(packageId);
    }

}
