package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private FruitRepository fruitRepository;

    @GetMapping("/package/all")
    private List<Package> getAllPackages(){
        return packageRepository.findAll();
    }

    @GetMapping("/package/fruit/{fruitId}")
    private List<Package> getAllPackagesByFruitId(@PathVariable UUID fruitId){
        return packageRepository.findByFruit_FruitId(fruitId);
    }

    @PostMapping("/package/add/{fruitId}")
    private ResponseEntity<?> addPackageByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Package packageRequest) throws Exception{
        if(!fruitRepository.existsById(fruitId)){
            return new ResponseEntity<>("There was no such fruit", HttpStatus.BAD_REQUEST);
        }

        if(packageRepository.existsByPackageNameAndFruit_FruitId(packageRequest.getPackageName(),fruitId)){
            return new ResponseEntity<>("You already have this package",HttpStatus.BAD_REQUEST);
        }
        Package packageTemp = fruitRepository.findById(fruitId)
                .map( fruit ->{
                    packageRequest.setFruit(fruit);
                    packageRequest.setUser(fruit.getUser());
                    packageRequest.setFruitName(fruit.getFruitName());
                    packageRepository.save(packageRequest);
                    return packageRequest;
                }).orElseThrow(() -> new Exception("Something went wrong"));
        if(!packageTemp.getPackageId().toString().isEmpty()){
            return new ResponseEntity<>("Package " + packageRequest.getPackageName() + " added successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/package/edit/{packageId}")
    public ResponseEntity<?> editPackageByPackageId(@PathVariable UUID packageId,@Valid @RequestBody Package packageRequest) throws Exception {
        if(!packageRepository.existsById(packageId)){
            return new ResponseEntity<>("There is no such package",HttpStatus.BAD_REQUEST);
        }

        Package packTemp = packageRepository.findById(packageId)
                .map(pack ->{
                    pack.setPackageName(packageRequest.getPackageName());
                    pack.setFruitName(packageRequest.getFruitName());
                    return packageRepository.save(pack);
        }).orElseThrow(() -> new Exception("Something went wrong"));

        if(!packTemp.getPackageName().isEmpty()){
            return new ResponseEntity<>("Package changed successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }
}
