package com.pipertzis.FarmHelper_BackEnd.Controllers;

import com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO.UserDTO;
import com.pipertzis.FarmHelper_BackEnd.Models.User;
import com.pipertzis.FarmHelper_BackEnd.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pipertzis.FarmHelper_BackEnd.AbstractClass.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    @Test
    public void testGetAllUserWhenThereAreNoUsers() throws Exception {
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/all");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
        verify(this.userService).getAllUsers();
    }

    @Test
    public void testGetAllUsersWhenThereIsAUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(UUID.randomUUID());
        userDTO.setSurname("testSurname");
        userDTO.setUsername("testUsername");
        userDTO.setPhoneNumber("123456789");
        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);

        when(this.userService.getAllUsers()).thenReturn(list);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/all");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(print())
                .andExpect(jsonPath("$.[0].username").value("testUsername"))
                .andExpect(jsonPath("$.[0].surname").value("testSurname"));

        verify(this.userService).getAllUsers();
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(this.userService.addUser(any())).thenReturn(userDTO);

        mvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(userDTO)));

        verify(userService).addUser(any());

    }

    @Test
    public void testEditUser() throws Exception {
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(this.userService.editUser(any(),any())).thenReturn(userDTO);

        mvc.perform(put("/user/edit/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(userDTO)))
                .andDo(print());

        verify(userService).editUser(any(),any());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(this.userService.deleteUser(any())).thenReturn(userDTO);

        mvc.perform(delete("/user/delete/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(userDTO)));

        verify(this.userService).deleteUser(any());
    }


}

