package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import com.pipertzis.FarmHelper_BackEnd.Services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllEntriesByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(entryService.getAllEntriesByUserId(userId));
    }

    @GetMapping("/fruit/{fruitId}")
    public ResponseEntity<?> getAllEntriesByFruitId(@PathVariable UUID fruitId){
        return ResponseEntity.ok(entryService.getAllEntriesByFruitId(fruitId));
    }

    @GetMapping("/variety/{varietyId}")
    public ResponseEntity<?> getAllEntriesByVarietyId(@PathVariable UUID varietyId){
        return ResponseEntity.ok(entryService.getAllEntriesByVarietyId(varietyId));
    }

    @PostMapping("/add/{varietyId}/{packageId}")
    public ResponseEntity<?> addEntryByVarietyIdAndPackageId(@PathVariable UUID varietyId,
                                                                @PathVariable UUID packageId,
                                                                @RequestBody Entry entry){

        return ResponseEntity.ok(entryService.addEntryByVarietyIdAndPackageId(varietyId, packageId, entry));
    }

    @PutMapping("/edit/{entryId}")
    public ResponseEntity<?> editEntryByEntryId(@PathVariable UUID entryId, @RequestBody Entry entry){
        return ResponseEntity.ok(entryService.editEntryByEntryId(entryId, entry));
    }

    @DeleteMapping("/delete/{entryId}")
    public ResponseEntity<?> deleteEntryByEntryId(@PathVariable UUID entryId){
        return ResponseEntity.ok(entryService.deleteEntryByEntryId(entryId));
    }


}
