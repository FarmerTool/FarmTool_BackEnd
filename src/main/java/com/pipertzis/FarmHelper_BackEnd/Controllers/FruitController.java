package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/fruit")
public class FruitController {


    @Autowired
    private FruitService fruitService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllFruitsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(fruitService.getAllFruitsByUserId(userId));
    }

    @GetMapping("/{fruitId}")
    public ResponseEntity<?> getFruitByFruitId(@PathVariable UUID fruitId) {

        return ResponseEntity.ok(fruitService.getFruitByFruitId(fruitId));
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addFruitByUserId(@PathVariable UUID userId,
                                              @Valid @RequestBody Fruit fruitRequest) {

        return ResponseEntity.ok(fruitService.addFruitByUserId(userId, fruitRequest));
    }

    @PutMapping("/edit/{fruitId}")
    public ResponseEntity<?> editFruitByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Fruit fruitRequest) throws Exception {
        return ResponseEntity.ok(fruitService.editFruitByFruitId(fruitId, fruitRequest));
    }

    @DeleteMapping("/delete/{fruitId}")
    public ResponseEntity<?> deleteFruitById(@PathVariable UUID fruitId) {

        return ResponseEntity.ok(fruitService.deleteFruitByFruitId(fruitId));
    }

}
