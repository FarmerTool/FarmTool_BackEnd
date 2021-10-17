package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> editUser(@PathVariable UUID userId, @Valid @RequestBody UserDTO userRequest) throws Exception {
        return ResponseEntity.ok(userService.editUser(userId,userRequest));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable UUID userId) throws Exception {
//        if (!userRepository.existsById(userId)) {
//            return new ResponseEntity<>("User with this " + userId + " User ID does not exist",HttpStatus.BAD_REQUEST);
//        }
//        if(!userVal.getUserId().toString().isEmpty()){
//            return new ResponseEntity<>("User " + userVal + " deleted successfully",HttpStatus.OK);
//        }
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
