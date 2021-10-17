package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import com.pipertzis.FarmHelper_BackEnd.Services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @GetMapping("/fruit/{fruitId}")
    public ResponseEntity<?> getAllPackagesByFruitId(@PathVariable UUID fruitId) {
        return ResponseEntity.ok(packageService.getAllPackagesByFruitId(fruitId));
    }

    @PostMapping("/add/{fruitId}")
    public ResponseEntity<?> addPackageByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Package packageRequest) throws Exception {
        return ResponseEntity.ok(packageService.addPackageByFruitId(fruitId, packageRequest));
    }

    @PutMapping("/edit/{packageId}")
    public ResponseEntity<?> editPackageByPackageId(@PathVariable UUID packageId, @Valid @RequestBody Package packageRequest) throws Exception {
        return ResponseEntity.ok(packageService.editPackageByPackageId(packageId, packageRequest));
    }
}
