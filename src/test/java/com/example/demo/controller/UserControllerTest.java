package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)  // Use SpringRunner for Spring context support
@WebMvcTest(UserController.class)  // Specify the controller to test
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc to simulate HTTP requests

    @MockBean
    private UserService userService;  // Mock the UserService

    @Test
    public void testGetUserById() throws Exception {
        // Mock the service layer
//        Employee mockUser = new Employee(1L, "John Doe", "john.doe@example.com", "1988-05-15", "HR", 80000);
//        Mockito.when(userService.getUserById(1L)).thenReturn(mockUser);

        // Perform GET request and assert the results
        mockMvc.perform(get("/users/{id}", 1L))  // URL of the API endpoint
               .andExpect(status().isOk())  // Assert HTTP status 200 OK
               .andExpect(jsonPath("$.name").value("John Doe"))  // Check if 'name' is 'John Doe'
               .andExpect(jsonPath("$.email").value("john.doe@example.com"));  // Check email
    }
}
