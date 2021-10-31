package com.pipertzis.FarmHelper_BackEnd.Services;
/*
    Created by Piper on 10/22/2021
*/

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.EntryDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private ModelMappingService modelMappingService;
    @Autowired
    private VarietyService varietyService;
    @Autowired
    private PackageService packageService;

    private final Class<EntryDTO> classToConvertTo = EntryDTO.class;


    public List<EntryDTO> getAllEntriesByUserId(UUID userId) {
        return entryRepository.findByUserUserId(userId)
                .stream().map(entry -> modelMappingService.convertModelToDTO(entry, classToConvertTo))
                .collect(Collectors.toList());
    }

    public List<EntryDTO> getAllEntriesByFruitId(UUID fruitId){
        return entryRepository.findByFruit_FruitId(fruitId)
                .stream().map(entry -> modelMappingService.convertModelToDTO(entry,classToConvertTo))
                .collect(Collectors.toList());
    }
    public List<EntryDTO> getAllEntriesByVarietyId(UUID varietyId){
        return entryRepository.findByVarietyVarietyId(varietyId)
                .stream().map(entry -> modelMappingService.convertModelToDTO(entry,classToConvertTo))
                .collect(Collectors.toList());
    }

    public EntryDTO addEntryByVarietyIdAndPackageId(UUID varietyId,
                                                    UUID packageId,
                                                    Entry entry){

        Entry entryToBeSaved = new Entry();
        Variety variety = varietyService.fetchVarietyByVarietyId(varietyId);
        entryToBeSaved.setFruit(variety.getFruit());
        entryToBeSaved.setVariety(variety);
        entryToBeSaved.setUser(variety.getUser());
        entryToBeSaved.setAPackage(packageService.fetchPackageByPackageId(packageId));

        entryToBeSaved.setAmount(entry.getAmount());
        entryToBeSaved.setPrice(entry.getPrice());
        entryToBeSaved.setWeight(entry.getWeight());
        entryToBeSaved.setDate(entry.getDate());

        return modelMappingService.convertModelToDTO(entryRepository.save(entryToBeSaved),classToConvertTo);
    }

    public EntryDTO editEntryByEntryId(UUID entryId, Entry entryRequest){
        Entry entrySaved = entryRepository.findById(entryId)
                .map(entryToBeSaved -> {

                    entryToBeSaved.setAmount(entryRequest.getAmount());
                    entryToBeSaved.setPrice(entryRequest.getPrice());
                    entryToBeSaved.setWeight(entryRequest.getWeight());
                    entryToBeSaved.setDate(entryRequest.getDate());
                    return entryRepository.save(entryToBeSaved);
                }).orElseThrow(EntityNotFoundException::new);

        return modelMappingService.convertModelToDTO(entrySaved,classToConvertTo);
    }

    public EntryDTO deleteEntryByEntryId(UUID entryId){
        Entry entryDeleted = entryRepository.findById(entryId)
                .map(entry -> {
                    entryRepository.delete(entry);
                    return entry;
                }).orElseThrow(EntityNotFoundException::new);

        return modelMappingService.convertModelToDTO(entryDeleted,classToConvertTo);
    }
}
