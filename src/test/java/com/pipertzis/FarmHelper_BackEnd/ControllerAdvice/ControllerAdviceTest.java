package com.pipertzis.FarmHelper_BackEnd.ControllerAdvice;

import com.pipertzis.FarmHelper_BackEnd.Controllers.UserController;
import com.pipertzis.FarmHelper_BackEnd.Exception.ApiExceptionHandler;
import com.pipertzis.FarmHelper_BackEnd.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class ControllerAdviceTest {
    @Autowired
    private UserController userController;
    @MockBean
    private UserService userService;

    @Test
    public void testControllerAdviceThrowNotFoundException() throws Exception {
        when(this.userService.getAllUsers()).thenThrow(new EntityNotFoundException());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/all");
        MockMvcBuilders.standaloneSetup(this.userController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testControllerAdviceThrowConnectException() throws Exception {
        given(this.userService.getAllUsers()).willAnswer(invocation -> { throw new ConnectException(); });

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/all");
        MockMvcBuilders.standaloneSetup(this.userController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isServiceUnavailable())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testControllerAdviceThrowPSQLException() throws Exception {
        ServerErrorMessage serverErrorMessage = new ServerErrorMessage("DConnection problem");
        given(this.userService.getAllUsers()).willAnswer(invocation -> { throw new PSQLException(serverErrorMessage); });

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/all");
        MockMvcBuilders.standaloneSetup(this.userController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }


}
