package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.FruitDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.FruitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ModelMappingService.class, UserService.class, FruitService.class})
@ExtendWith(SpringExtension.class)
public class FruitServiceTest {
    @MockBean
    private FruitRepository fruitRepository;

    @Autowired
    private FruitService fruitService;

    @MockBean
    private ModelMappingService modelMappingService;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllFruitsByUserId() {
        when(this.fruitRepository.findByUser_userId(any())).thenReturn(new ArrayList<Fruit>());
        assertTrue(this.fruitService.getAllFruitsByUserId(UUID.randomUUID()).isEmpty());
        verify(this.fruitRepository).findByUser_userId(any());
    }

    @Test
    public void testGetAllFruitsByUserId2() {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());

        ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
        fruitList.add(fruit);
        when(this.fruitRepository.findByUser_userId(any())).thenReturn(fruitList);
        assertEquals(1, this.fruitService.getAllFruitsByUserId(UUID.randomUUID()).size());
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        verify(this.fruitRepository).findByUser_userId(any());
    }

    @Test
    public void testGetAllFruitsByUserId3() {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(UUID.randomUUID());
        user1.setUsername("janedoe");
        user1.setPhoneNumber("4105551212");
        user1.setSurname("Doe");

        Fruit fruit1 = new Fruit();
        fruit1.setUser(user1);
        fruit1.setFruitName("Fruit Name");
        fruit1.setFruitId(UUID.randomUUID());

        ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
        fruitList.add(fruit1);
        fruitList.add(fruit);
        when(this.fruitRepository.findByUser_userId(any())).thenReturn(fruitList);
        assertEquals(2, this.fruitService.getAllFruitsByUserId(UUID.randomUUID()).size());
        verify(this.modelMappingService, times(2)).convertModelToDTO(any(), any());
        verify(this.fruitRepository).findByUser_userId(any());
    }

    @Test
    public void testGetFruitByFruitId() {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());
        when(this.fruitRepository.getById(any())).thenReturn(fruit);
        assertSame(fruitDTO, this.fruitService.getFruitByFruitId(UUID.randomUUID()));
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        verify(this.fruitRepository).getById(any());
    }

    @Test
    public void testAddFruitByUserId() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");
        when(this.userService.fetchUserById(any())).thenReturn(user);

        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(UUID.randomUUID());
        user1.setUsername("janedoe");
        user1.setPhoneNumber("4105551212");
        user1.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user1);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());
        when(this.fruitRepository.save(any())).thenReturn(fruit);
        UUID userId = UUID.randomUUID();

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(UUID.randomUUID());
        user2.setUsername("janedoe");
        user2.setPhoneNumber("4105551212");
        user2.setSurname("Doe");

        Fruit fruit1 = new Fruit();
        fruit1.setUser(user2);
        fruit1.setFruitName("Fruit Name");
        fruit1.setFruitId(UUID.randomUUID());
        assertSame(fruitDTO, this.fruitService.addFruitByUserId(userId, fruit1));
        verify(this.userService).fetchUserById(any());
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        verify(this.fruitRepository).save(any());
        assertSame(user, fruit1.getUser());
    }

    @Test
    public void testEditFruitByFruitId() {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());
        Optional<Fruit> ofResult = Optional.of(fruit);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(UUID.randomUUID());
        user1.setUsername("janedoe");
        user1.setPhoneNumber("4105551212");
        user1.setSurname("Doe");

        Fruit fruit1 = new Fruit();
        fruit1.setUser(user1);
        fruit1.setFruitName("Fruit Name");
        fruit1.setFruitId(UUID.randomUUID());
        when(this.fruitRepository.save(any())).thenReturn(fruit1);
        when(this.fruitRepository.findById(any())).thenReturn(ofResult);
        UUID fruitId = UUID.randomUUID();

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(UUID.randomUUID());
        user2.setUsername("janedoe");
        user2.setPhoneNumber("4105551212");
        user2.setSurname("Doe");

        Fruit fruit2 = new Fruit();
        fruit2.setUser(user2);
        fruit2.setFruitName("Fruit Name");
        fruit2.setFruitId(UUID.randomUUID());
        assertSame(fruitDTO, this.fruitService.editFruitByFruitId(fruitId, fruit2));
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        verify(this.fruitRepository).findById(any());
        verify(this.fruitRepository).save(any());
    }

    @Test
    public void testDeleteFruitByFruitId() {
        FruitDTO fruitDTO = new FruitDTO();
        fruitDTO.setUserId(UUID.randomUUID());
        fruitDTO.setUsername("janedoe");
        fruitDTO.setFruitName("Fruit Name");
        fruitDTO.setSurname("Doe");
        fruitDTO.setFruitId(UUID.randomUUID());
        when(this.modelMappingService.convertModelToDTO(any(), any())).thenReturn(fruitDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());
        Optional<Fruit> ofResult = Optional.of(fruit);
        doNothing().when(this.fruitRepository).delete(any());
        when(this.fruitRepository.findById(any())).thenReturn(ofResult);
        assertSame(fruitDTO, this.fruitService.deleteFruitByFruitId(UUID.randomUUID()));
        verify(this.modelMappingService).convertModelToDTO(any(), any());
        verify(this.fruitRepository).delete(any());
        verify(this.fruitRepository).findById(any());
    }

    @Test
    public void testFetchFruitById() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

        Fruit fruit = new Fruit();
        fruit.setUser(user);
        fruit.setFruitName("Fruit Name");
        fruit.setFruitId(UUID.randomUUID());
        when(this.fruitRepository.getById(any())).thenReturn(fruit);
        assertSame(fruit, this.fruitService.fetchFruitById(UUID.randomUUID()));
        verify(this.fruitRepository).getById(any());
    }
}

