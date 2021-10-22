package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.VarietyFruitUserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import com.pipertzis.FarmHelper_BackEnd.Repositories.VarietyRepository;
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

@ContextConfiguration(classes = {VarietyService.class, ModelMappingService.class, FruitService.class})
@ExtendWith(SpringExtension.class)
public class VarietyServiceTest {
    @MockBean
    private FruitService fruitService;

    @MockBean
    private ModelMappingService modelMappingService;

    @MockBean
    private VarietyRepository varietyRepository;

    @Autowired
    private VarietyService varietyService;

    @Test
    public void testGetAllVarietiesByUserId() {
        when(this.varietyRepository.findByUser_userId((UUID) any())).thenReturn(new ArrayList<Variety>());
        assertTrue(this.varietyService.getAllVarietiesByUserId(UUID.randomUUID()).isEmpty());
        verify(this.varietyRepository).findByUser_userId((UUID) any());
    }

    @Test
    public void testGetAllVarietiesByUserId2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);

        ArrayList<Variety> varietyList = new ArrayList<Variety>();
        varietyList.add(variety);
        when(this.varietyRepository.findByUser_userId((UUID) any())).thenReturn(varietyList);
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(new VarietyFruitUserDTO());
        assertEquals(1, this.varietyService.getAllVarietiesByUserId(UUID.randomUUID()).size());
        verify(this.varietyRepository).findByUser_userId((UUID) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }

    @Test
    public void testGetAllVarietiesByFruitId() {
        when(this.varietyRepository.findByFruit_fruitId((UUID) any())).thenReturn(new ArrayList<Variety>());
        assertTrue(this.varietyService.getAllVarietiesByFruitId(UUID.randomUUID()).isEmpty());
        verify(this.varietyRepository).findByFruit_fruitId((UUID) any());
    }

    @Test
    public void testGetAllVarietiesByFruitId2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);

        ArrayList<Variety> varietyList = new ArrayList<Variety>();
        varietyList.add(variety);
        when(this.varietyRepository.findByFruit_fruitId((UUID) any())).thenReturn(varietyList);
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(new VarietyFruitUserDTO());
        assertEquals(1, this.varietyService.getAllVarietiesByFruitId(UUID.randomUUID()).size());
        verify(this.varietyRepository).findByFruit_fruitId((UUID) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }

    @Test
    public void testGetVarietyByVarietyId() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);
        when(this.varietyRepository.getById((UUID) any())).thenReturn(variety);
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(varietyFruitUserDTO);
        assertSame(varietyFruitUserDTO, this.varietyService.getVarietyByVarietyId(UUID.randomUUID()));
        verify(this.varietyRepository).getById((UUID) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }

    @Test
    public void testAddVarietyByFruitId() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);
        when(this.varietyRepository.save((Variety) any())).thenReturn(variety);
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(varietyFruitUserDTO);

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
        when(this.fruitService.fetchFruitById((UUID) any())).thenReturn(fruit1);
        UUID fruitId = UUID.randomUUID();

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setUserId(UUID.randomUUID());
        user3.setUsername("janedoe");
        user3.setPhoneNumber("4105551212");
        user3.setSurname("Doe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setUserId(UUID.randomUUID());
        user4.setUsername("janedoe");
        user4.setPhoneNumber("4105551212");
        user4.setSurname("Doe");

        Fruit fruit2 = new Fruit();
        fruit2.setUser(user4);
        fruit2.setFruitName("Fruit Name");
        fruit2.setFruitId(UUID.randomUUID());

        Variety variety1 = new Variety();
        variety1.setVarietyName("Variety Name");
        variety1.setUser(user3);
        variety1.setVarietyId(UUID.randomUUID());
        variety1.setFruit(fruit2);
        assertSame(varietyFruitUserDTO, this.varietyService.addVarietyByFruitId(fruitId, variety1));
        verify(this.varietyRepository).save((Variety) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
        verify(this.fruitService).fetchFruitById((UUID) any());
        assertSame(fruit1, variety1.getFruit());
        assertSame(user2, variety1.getUser());
    }

    @Test
    public void testEditVarietyNameByVarietyId() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);
        Optional<Variety> ofResult = Optional.<Variety>of(variety);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(UUID.randomUUID());
        user2.setUsername("janedoe");
        user2.setPhoneNumber("4105551212");
        user2.setSurname("Doe");

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setUserId(UUID.randomUUID());
        user3.setUsername("janedoe");
        user3.setPhoneNumber("4105551212");
        user3.setSurname("Doe");

        Fruit fruit1 = new Fruit();
        fruit1.setUser(user3);
        fruit1.setFruitName("Fruit Name");
        fruit1.setFruitId(UUID.randomUUID());

        Variety variety1 = new Variety();
        variety1.setVarietyName("Variety Name");
        variety1.setUser(user2);
        variety1.setVarietyId(UUID.randomUUID());
        variety1.setFruit(fruit1);
        when(this.varietyRepository.save((Variety) any())).thenReturn(variety1);
        when(this.varietyRepository.findById((UUID) any())).thenReturn(ofResult);
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(varietyFruitUserDTO);
        UUID varietyId = UUID.randomUUID();

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setUserId(UUID.randomUUID());
        user4.setUsername("janedoe");
        user4.setPhoneNumber("4105551212");
        user4.setSurname("Doe");

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setUserId(UUID.randomUUID());
        user5.setUsername("janedoe");
        user5.setPhoneNumber("4105551212");
        user5.setSurname("Doe");

        Fruit fruit2 = new Fruit();
        fruit2.setUser(user5);
        fruit2.setFruitName("Fruit Name");
        fruit2.setFruitId(UUID.randomUUID());

        Variety variety2 = new Variety();
        variety2.setVarietyName("Variety Name");
        variety2.setUser(user4);
        variety2.setVarietyId(UUID.randomUUID());
        variety2.setFruit(fruit2);
        assertSame(varietyFruitUserDTO, this.varietyService.editVarietyNameByVarietyId(varietyId, variety2));
        verify(this.varietyRepository).findById((UUID) any());
        verify(this.varietyRepository).save((Variety) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }

    @Test
    public void testDeleteVarietyByVarietyId() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setPhoneNumber("4105551212");
        user.setSurname("Doe");

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

        Variety variety = new Variety();
        variety.setVarietyName("Variety Name");
        variety.setUser(user);
        variety.setVarietyId(UUID.randomUUID());
        variety.setFruit(fruit);
        Optional<Variety> ofResult = Optional.<Variety>of(variety);
        doNothing().when(this.varietyRepository).delete((Variety) any());
        when(this.varietyRepository.findById((UUID) any())).thenReturn(ofResult);
        VarietyFruitUserDTO varietyFruitUserDTO = new VarietyFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(varietyFruitUserDTO);
        assertSame(varietyFruitUserDTO, this.varietyService.deleteVarietyByVarietyId(UUID.randomUUID()));
        verify(this.varietyRepository).delete((Variety) any());
        verify(this.varietyRepository).findById((UUID) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }
}

