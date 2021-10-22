package com.pipertzis.FarmHelper_BackEnd.Services;


import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ModelMappingService.class, UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    ModelMappingService modelMappingService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testGetAllUsersWhenIsEmpty(){
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        assertTrue(this.userService.getAllUsers().isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    public void testGetAllUsersWhenThereIsOneUser(){
        User user = new User();
        user.setEmail("testMail");
        user.setUserId(UUID.randomUUID());
        user.setSurname("testSurname");
        user.setUsername("testUsername");
        user.setPhoneNumber("123456789");
        List<User> list = new ArrayList<>();
        list.add(user);
        when(this.userRepository.findAll()).thenReturn(list);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(UUID.randomUUID());
        userDTO.setUsername("testUsername");
        userDTO.setSurname("testSurname");
        userDTO.setEmail("testMail");
        userDTO.setPhoneNumber("123456789");

        when(modelMappingService.convertModelToDTO(any(),any())).thenReturn(userDTO);

        assertEquals(1,this.userService.getAllUsers().size());
        assertSame(userDTO,userService.getAllUsers().get(0));

        verify(userRepository,times(2)).findAll();
        verify(this.modelMappingService,times(2)).convertModelToDTO(any(), any());

    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setEmail("testMail");
        user.setUserId(UUID.randomUUID());
        user.setSurname("testSurname");
        user.setUsername("testUsername");
        user.setPhoneNumber("123456789");

        when(this.userRepository.save(any())).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(UUID.randomUUID());
        userDTO.setUsername("testUsername");
        userDTO.setSurname("testSurname");
        userDTO.setEmail("testMail");
        userDTO.setPhoneNumber("123456789");

        when(this.modelMappingService.convertModelToDTO(any(),any())).thenReturn(userDTO);
        assertSame(this.userService.addUser(user),userDTO);

        verify(this.userRepository).save(user);
        verify(this.modelMappingService).convertModelToDTO(any(), any());

    }

    @Test
    public void editUser(){
        User user = new User();
        user.setEmail("testMail");
        user.setUserId(UUID.randomUUID());
        user.setSurname("testSurname");
        user.setUsername("testUsername");
        user.setPhoneNumber("123456789");
        when(this.userRepository.save(user)).thenReturn(user);

        User editedUser = new User();
        editedUser.setEmail("editedMail");
        editedUser.setUserId(UUID.randomUUID());
        editedUser.setSurname("testSurname");
        editedUser.setUsername("testUsername");
        editedUser.setPhoneNumber("123456789");

        when(this.userRepository.save(any())).thenReturn(editedUser);
        when(this.userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(UUID.randomUUID());
        userDTO.setUsername("testUsername");
        userDTO.setSurname("testSurname");
        userDTO.setEmail("editedMail");
        userDTO.setPhoneNumber("123456789");

        when(this.modelMappingService.convertModelToDTO(any(),any())).thenReturn(userDTO);

        assertSame(userDTO,this.userService.editUser(UUID.randomUUID(),userDTO));

    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setEmail("testMail");
        user.setUserId(UUID.randomUUID());
        user.setSurname("testSurname");
        user.setUsername("testUsername");
        user.setPhoneNumber("123456789");

        Optional<User> ofResult = Optional.of(user);
        doNothing().when(this.userRepository).deleteById(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setUserId(UUID.randomUUID());
        userDTO.setUsername("janedoe");
        userDTO.setPhoneNumber("4105551212");
        userDTO.setSurname("Doe");
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(userDTO);
        assertSame(userDTO, this.userService.deleteUser(UUID.randomUUID()));
        verify(this.userRepository).deleteById(any());
        verify(this.userRepository).findById(any());
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    public void testFetchUserById() {
        User user = new User();
        user.setEmail("testMail");
        user.setUserId(UUID.randomUUID());
        user.setSurname("testSurname");
        user.setUsername("testUsername");
        user.setPhoneNumber("123456789");
        when(this.userRepository.getById(any())).thenReturn(user);
        assertSame(user, this.userService.fetchUserById(UUID.randomUUID()));
        verify(this.userRepository).getById(any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

}
