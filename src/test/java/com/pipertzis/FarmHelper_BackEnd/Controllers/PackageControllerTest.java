package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.PackageDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import com.pipertzis.FarmHelper_BackEnd.Services.PackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static com.pipertzis.FarmHelper_BackEnd.AbstractClass.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {PackageController.class})
@ExtendWith(SpringExtension.class)
public class PackageControllerTest {
    @Autowired
    private PackageController packageController;

    @MockBean
    private PackageService packageService;

    @Test
    public void testGetAllPackages() throws Exception {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName("testPackageName");

        when(this.packageService.getAllPackages()).thenReturn(Collections.singletonList(packageDTO));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/package/all");
        MockMvcBuilders.standaloneSetup(this.packageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].packageName").value("testPackageName"))
                .andDo(print());

        verify(this.packageService).getAllPackages();
    }


    @Test
    public void testGetAllPackagesByFruitId() throws Exception {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName("testPackageName");
        when(this.packageService.getAllPackagesByFruitId(any())).thenReturn(Collections.singletonList(packageDTO));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/package/fruit/{fruitId}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.packageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].packageName").value("testPackageName"));
        verify(this.packageService).getAllPackagesByFruitId(any());
    }

    @Test
    public void testAddPackageByFruitId() throws Exception {
        Package pack = new Package();
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName("testPackageName");

        when(this.packageService.addPackageByFruitId(any(), any())).thenReturn(packageDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/package/add/{fruitId}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(pack));

        MockMvcBuilders.standaloneSetup(this.packageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(packageDTO)));
        verify(this.packageService).addPackageByFruitId(any(),any());
    }

    @Test
    public void testEditPackageByPackageId() throws Exception {
        Package pack = new Package();
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName("testPackageName");

        when(this.packageService.editPackageByPackageId(any(), any())).thenReturn(packageDTO);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/package/edit/{packageId}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(pack));
        MockMvcBuilders.standaloneSetup(this.packageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(packageDTO)));
        verify(this.packageService).editPackageByPackageId(any(),any());
    }
}

