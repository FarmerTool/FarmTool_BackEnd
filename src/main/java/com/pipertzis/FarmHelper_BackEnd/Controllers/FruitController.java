package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class FruitController {

    @Autowired
    private FruitRepository fruitRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/fruits/{userId}")
    public List<Fruit> getAllFruitsByUserId(@PathVariable UUID userId){
        return fruitRepository.findByUser_userId(userId);
    }

    @PostMapping("/fruits/{userId}")
    public Fruit addFruitByUserId(@PathVariable UUID userId,
                          @Valid @RequestBody Fruit fruitRequest) throws Exception {
        if(!userRepository.existsById(userId)){
            throw new Exception("User with this " + userId + " not found");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    fruitRequest.setUser(user);
                    return fruitRepository.save(fruitRequest);
                }).orElseThrow(()-> new Exception("Something went wrong"));
    }


    @PutMapping("/fruits/edit/{fruitId}")
    public Fruit editFruitByFruitId(@PathVariable UUID fruitId,@Valid @RequestBody Fruit fruitRequest) throws Exception {
        return fruitRepository.findById(fruitId)
                .map(fruit->{
                    fruit.setFruitName(fruitRequest.getFruitName());
                    return fruitRepository.save(fruit);
                }).orElseThrow(() -> new Exception("Something went wrong"));

    }

}
