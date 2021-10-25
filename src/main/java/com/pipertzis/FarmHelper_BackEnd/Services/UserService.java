package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMappingService modelMappingService;

    private final Class<UserDTO> classTypeToCoverTo = UserDTO.class;

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMappingService.convertModelToDTO(user, classTypeToCoverTo))
                .collect(Collectors.toList());
    }

    public UserDTO addUser(User user) {
        return modelMappingService.convertModelToDTO(userRepository.save(user), classTypeToCoverTo);
    }

    public UserDTO editUser(UUID userId, UserDTO userRequest) {
        User editedUser = userRepository.findById(userId)
                    .map(user -> {
                        user.setEmail(userRequest.getEmail());
                        user.setUsername(userRequest.getUsername());
                        user.setPhoneNumber(userRequest.getPhoneNumber());
                        user.setSurname(userRequest.getSurname());
                        return userRepository.save(user);
                    }).orElseThrow(EntityNotFoundException::new);

        return modelMappingService.convertModelToDTO(editedUser, classTypeToCoverTo);
    }

    public UserDTO deleteUser(UUID userId) {
        User deletedUser = userRepository.findById(userId)
                .map(user -> {
                    userRepository.deleteById(userId);
                    return user;
                }).orElseThrow(EntityNotFoundException::new);

        return modelMappingService.convertModelToDTO(deletedUser, classTypeToCoverTo);

    }


    public User fetchUserById(UUID userId) {
        return userRepository.getById(userId);
    }

}
