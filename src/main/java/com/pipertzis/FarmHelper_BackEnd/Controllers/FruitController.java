package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserFruitDTO;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import com.pipertzis.FarmHelper_BackEnd.Services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class FruitController {


    @Autowired
    private FruitService fruitService;

    @GetMapping("/fruits/user/{userId}")
    public ResponseEntity<?> getAllFruitsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(fruitService.getAllFruitsByUserId(userId));
    }

    @GetMapping("fruits/fruit/{fruitId}")
    public ResponseEntity<?> getFruitByFruitId(@PathVariable UUID fruitId) {

        return ResponseEntity.ok(fruitService.getFruitByFruitId(fruitId));
    }

    @PostMapping("/fruits/add/{userId}")
    public ResponseEntity<?> addFruitByUserId(@PathVariable UUID userId,
                                  @Valid @RequestBody Fruit fruitRequest){

        return ResponseEntity.ok(fruitService.addFruitByUserId(userId,fruitRequest));
    }

    @PutMapping("/fruits/edit/{fruitId}")
    public ResponseEntity<?> editFruitByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Fruit fruitRequest) throws Exception {
        return ResponseEntity.ok(fruitService.editFruitByFruitId(fruitId,fruitRequest));
    }

    @DeleteMapping("fruits/delete/{fruitId}")
    public ResponseEntity<?> deleteFruitById(@PathVariable UUID fruitId) throws Exception {

        return ResponseEntity.ok(fruitService.deleteFruitByFruitId(fruitId));
    }

}
