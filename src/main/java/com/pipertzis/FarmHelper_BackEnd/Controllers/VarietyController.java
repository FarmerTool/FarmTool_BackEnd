package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/variety")
public class VarietyController {


    @Autowired
    private VarietyService varietyService;

    @GetMapping("/{varietyId}")
    public ResponseEntity<?> getVarietyById(@PathVariable UUID varietyId) {
        return ResponseEntity.ok(varietyService.getVarietyByVarietyId(varietyId));
    }

    @GetMapping("/all/{fruitId}")
    public ResponseEntity<?> getAllVarietiesByFruitId(@PathVariable UUID fruitId) {
        return ResponseEntity.ok(varietyService.getAllVarietiesByFruitId(fruitId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllVarietiesByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(varietyService.getAllVarietiesByUserId(userId));
    }

    @PostMapping("/add/{fruitId}")
    public ResponseEntity<?> addVarietyByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Variety varietyRequest) {
        return ResponseEntity.ok(varietyService.addVarietyByFruitId(fruitId, varietyRequest));
    }

    @PutMapping("/edit/{varietyId}")
    public ResponseEntity<?> editVarietyNameById(@PathVariable UUID varietyId, @Valid @RequestBody Variety varietyRequest) {
        return ResponseEntity.ok(varietyService.editVarietyNameByVarietyId(varietyId, varietyRequest));
    }

    @DeleteMapping("/delete/{varietyId}")
    public ResponseEntity<?> deleteVarietyByVarietyId(@PathVariable UUID varietyId) {
        return ResponseEntity.ok(varietyService.deleteVarietyByVarietyId(varietyId));
    }

}
