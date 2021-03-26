package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FruitController {

    @Autowired
    private FruitRepository fruitRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/fruits/user/{userId}")
    public List<Fruit> getAllFruitsByUserId(@PathVariable UUID userId) {
        return fruitRepository.findByUser_userId(userId);
    }

    @GetMapping("fruits/fruit/{fruitId}")
    public Optional<Fruit> getFruitByFruitId(@PathVariable UUID fruitId) {
        return fruitRepository.findById(fruitId);
    }

    @PostMapping("/fruits/add/{userId}")
    public Fruit addFruitByUserId(@PathVariable UUID userId,
                                  @Valid @RequestBody Fruit fruitRequest) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (fruitRepository.existsFruitByFruitNameAndUser_userId(fruitRequest.getFruitName(), userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userRepository.findById(userId)
                .map(user -> {
                    fruitRequest.setUser(user);
                    return fruitRepository.save(fruitRequest);
                }).orElseThrow(() -> new Exception("Something went wrong"));
    }

    @PutMapping("/fruits/edit/{fruitId}")
    public Fruit editFruitByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Fruit fruitRequest) throws Exception {
        if (fruitRepository.existsFruitByFruitName(fruitRequest.getFruitName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruit.setFruitName(fruitRequest.getFruitName());
                    return fruitRepository.save(fruit);
                }).orElseThrow(() -> new Exception("Something went wrong"));

    }

    @DeleteMapping("fruits/delete/{fruitId}")
    public ResponseEntity<?> deleteFruitById(@PathVariable UUID fruitId) throws Exception {
        if (!fruitRepository.existsById(fruitId)) {
            return new ResponseEntity<>("Fruit with this " + fruitId + " Fruit ID doesn't exist", HttpStatus.BAD_REQUEST);
        }

        Fruit fruitVal = fruitRepository.findById(fruitId)
                .map(fruit -> {
                    fruitRepository.delete(fruit);
                    return fruit;
                }).orElseThrow(() -> new Exception("Something went wrong"));

        if(!fruitVal.getFruitId().toString().isEmpty()){
            return new ResponseEntity<>("Fruit " + fruitId + " deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

}
