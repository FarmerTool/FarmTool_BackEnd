package com.pipertzis.FarmHelper_BackEnd.Services;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.PackageFruitUserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Repositories.PackageRepository;
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

@ContextConfiguration(classes = {PackageService.class, ModelMappingService.class, FruitService.class})
@ExtendWith(SpringExtension.class)
public class PackageServiceTest {
    @MockBean
    private FruitService fruitService;

    @MockBean
    private ModelMappingService modelMappingService;

    @MockBean
    private PackageRepository packageRepository;

    @Autowired
    private PackageService packageService;

    @Test
    public void testGetAllPackages() {
        when(this.packageRepository.findAll()).thenReturn(new ArrayList<Package>());
        assertTrue(this.packageService.getAllPackages().isEmpty());
        verify(this.packageRepository).findAll();
    }

    @Test
    public void testGetAllPackages2() {
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

        Package resultPackage = new Package();
        resultPackage.setUser(user);
        resultPackage.setPackageName("java.text");
        resultPackage.setPackageId(UUID.randomUUID());
        resultPackage.setFruitName("Fruit Name");
        resultPackage.setFruit(fruit);

        ArrayList<Package> resultPackageList = new ArrayList<Package>();
        resultPackageList.add(resultPackage);
        when(this.packageRepository.findAll()).thenReturn(resultPackageList);
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(new PackageFruitUserDTO());
        assertEquals(1, this.packageService.getAllPackages().size());
        verify(this.packageRepository).findAll();
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }

    @Test
    public void testGetAllPackagesByFruitId() {
        when(this.packageRepository.findByFruit_FruitId((UUID) any())).thenReturn(new ArrayList<Package>());
        assertTrue(this.packageService.getAllPackagesByFruitId(UUID.randomUUID()).isEmpty());
        verify(this.packageRepository).findByFruit_FruitId((UUID) any());
        assertTrue(this.packageService.getAllPackages().isEmpty());
    }

    @Test
    public void testGetAllPackagesByFruitId2() {
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

        Package resultPackage = new Package();
        resultPackage.setUser(user);
        resultPackage.setPackageName("java.text");
        resultPackage.setPackageId(UUID.randomUUID());
        resultPackage.setFruitName("Fruit Name");
        resultPackage.setFruit(fruit);

        ArrayList<Package> resultPackageList = new ArrayList<Package>();
        resultPackageList.add(resultPackage);
        when(this.packageRepository.findByFruit_FruitId((UUID) any())).thenReturn(resultPackageList);
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(new PackageFruitUserDTO());
        assertEquals(1, this.packageService.getAllPackagesByFruitId(UUID.randomUUID()).size());
        verify(this.packageRepository).findByFruit_FruitId((UUID) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
        assertTrue(this.packageService.getAllPackages().isEmpty());
    }

    @Test
    public void testAddPackageByFruitId() {
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

        Package resultPackage = new Package();
        resultPackage.setUser(user);
        resultPackage.setPackageName("java.text");
        resultPackage.setPackageId(UUID.randomUUID());
        resultPackage.setFruitName("Fruit Name");
        resultPackage.setFruit(fruit);
        when(this.packageRepository.save((Package) any())).thenReturn(resultPackage);
        PackageFruitUserDTO packageFruitUserDTO = new PackageFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(packageFruitUserDTO);

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

        Package resultPackage1 = new Package();
        resultPackage1.setUser(user3);
        resultPackage1.setPackageName("java.text");
        resultPackage1.setPackageId(UUID.randomUUID());
        resultPackage1.setFruitName("Fruit Name");
        resultPackage1.setFruit(fruit2);
        assertSame(packageFruitUserDTO, this.packageService.addPackageByFruitId(fruitId, resultPackage1));
        verify(this.packageRepository).save((Package) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
        verify(this.fruitService).fetchFruitById((UUID) any());
        assertSame(fruit1, resultPackage1.getFruit());
        assertSame(user2, resultPackage1.getUser());
        assertEquals("Fruit Name", resultPackage1.getFruitName());
        assertTrue(this.packageService.getAllPackages().isEmpty());
    }

    @Test
    public void testEditPackageByPackageId() {
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

        Package resultPackage = new Package();
        resultPackage.setUser(user);
        resultPackage.setPackageName("java.text");
        resultPackage.setPackageId(UUID.randomUUID());
        resultPackage.setFruitName("Fruit Name");
        resultPackage.setFruit(fruit);
        Optional<Package> ofResult = Optional.<Package>of(resultPackage);

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

        Package resultPackage1 = new Package();
        resultPackage1.setUser(user2);
        resultPackage1.setPackageName("java.text");
        resultPackage1.setPackageId(UUID.randomUUID());
        resultPackage1.setFruitName("Fruit Name");
        resultPackage1.setFruit(fruit1);
        when(this.packageRepository.save((Package) any())).thenReturn(resultPackage1);
        when(this.packageRepository.findById((UUID) any())).thenReturn(ofResult);
        PackageFruitUserDTO packageFruitUserDTO = new PackageFruitUserDTO();
        when(this.modelMappingService.convertModelToDTO((Object) any(), (Class<Object>) any()))
                .thenReturn(packageFruitUserDTO);
        UUID packageId = UUID.randomUUID();

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

        Package resultPackage2 = new Package();
        resultPackage2.setUser(user4);
        resultPackage2.setPackageName("java.text");
        resultPackage2.setPackageId(UUID.randomUUID());
        resultPackage2.setFruitName("Fruit Name");
        resultPackage2.setFruit(fruit2);
        assertSame(packageFruitUserDTO, this.packageService.editPackageByPackageId(packageId, resultPackage2));
        verify(this.packageRepository).findById((UUID) any());
        verify(this.packageRepository).save((Package) any());
        verify(this.modelMappingService).convertModelToDTO((Object) any(), (Class<Object>) any());
    }
}

