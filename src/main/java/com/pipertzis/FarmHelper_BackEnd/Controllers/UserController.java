package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user/add")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/edit/{userId}")
    public User editUser(@PathVariable UUID userId, @Valid @RequestBody User userRequest) throws Exception {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setEmail(userRequest.getEmail());
                    user.setName(userRequest.getName());
                    user.setPassword(userRequest.getPassword());
                    user.setPhoneNumber(userRequest.getPhoneNumber());
                    user.setSurname(userRequest.getSurname());
                    return userRepository.save(user);
                }).orElseThrow(() -> new Exception("Something went wrong"));
    }

    @DeleteMapping("user/delete/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable UUID userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            return new ResponseEntity<>("User with this " + userId + " User ID does not exist",HttpStatus.BAD_REQUEST);
        }

        User userVal = userRepository.findById(userId)
                .map(user -> {
                    userRepository.deleteById(userId);
                    return user;
                }).orElseThrow(() -> new Exception("Couldn't delete the User"));
        if(!userVal.getUserId().toString().isEmpty()){
            return new ResponseEntity<>("User " + userVal + " deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

}
