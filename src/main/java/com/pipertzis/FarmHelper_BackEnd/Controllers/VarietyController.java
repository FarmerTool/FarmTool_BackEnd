package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import com.pipertzis.FarmHelper_BackEnd.Repositories.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class VarietyController {

    @Autowired
    private VarietyRepository varietyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FruitRepository fruitRepository;

    @GetMapping("/variety/{varietyId}")
    public Optional<Variety> getVarietyById(@PathVariable UUID varietyId){
        return varietyRepository.findById(varietyId);
    }

    @GetMapping("/variety/all/{fruitId}")
    public List<Variety> getAllVarietiesByFruitId(@PathVariable UUID fruitId){
        return varietyRepository.findByFruit_fruitId(fruitId);
    }

    @GetMapping("/variety/user/{userId}")
    public List<Variety> getAllVarietiesByUserId(@PathVariable UUID userId){
        return varietyRepository.findByUser_userId(userId);
    }

    @PostMapping("/variety/add/{fruitId}")
    public Variety addVarietyByFruitId(@PathVariable UUID fruitId, @Valid @RequestBody Variety varietyRequest) throws Exception{

        if(!fruitRepository.existsById(fruitId)){
            throw  new Exception("Fruit with this " + fruitId + " Fruit ID does not exist");
        }

        return fruitRepository.findById(fruitId)
                .map( fruit -> {
                    varietyRequest.setFruit(fruit);
                    varietyRequest.setUser(fruit.getUser());
                    return varietyRepository.save(varietyRequest);
                }).orElseThrow(() -> new Exception("Something went wrong"));
    }

    @PutMapping("/variety/edit/{varietyId}")
    public ResponseEntity<?> editVarietyNameById(@PathVariable UUID varietyId,@Valid @RequestBody Variety varietyRequest) throws Exception {
        if(!varietyRepository.existsById(varietyId)){
            return new ResponseEntity<>("There is no such variety", HttpStatus.BAD_REQUEST);
        }

        Variety varVal = varietyRepository.findById(varietyId)
                .map( variety -> {
                    variety.setVarietyName(varietyRequest.getVarietyName());
                    return varietyRepository.save(variety);
                }).orElseThrow(() -> new Exception("Something went wrong"));
        if(!varVal.getVarietyId().toString().isEmpty()){

            return new ResponseEntity<>("Variety edited successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/variety/delete/{varietyId}")
    public ResponseEntity<?> deleteVarietyByVarietyId(@PathVariable UUID varietyId) throws Exception {
        if(!varietyRepository.existsById(varietyId)){
            return new ResponseEntity<>("There was no such variety", HttpStatus.BAD_REQUEST);
        }

        Variety varVal = varietyRepository.findById(varietyId)
                .map(variety -> {
            varietyRepository.delete(variety);
            return variety;
        }).orElseThrow(() -> new Exception("something went wrong"));

        if(!varVal.getVarietyId().toString().isEmpty()){
            return new ResponseEntity<>("Variety " + varietyId + " deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

}
