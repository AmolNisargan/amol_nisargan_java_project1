package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Employee;
import com.example.demo.service.UserService;

public class UserServiceTest {

    @Mock
    private List<Employee> mockDatabase;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_Failure() {
        // Create a mock stream
        Stream<Employee> mockStream = mock(Stream.class);

        // Mock the behavior of stream().filter().findFirst() to return Optional.empty()
        when(mockStream.filter(any())).thenReturn(mockStream);
        when(mockStream.findFirst()).thenReturn(Optional.empty());

        // Mock the database to return the mocked stream when stream() is called
        when(mockDatabase.stream()).thenReturn(mockStream);

        // Use assertThrows to verify the exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(999L); // Should throw exception
        });

        // Check that the exception message is correct
        assertEquals("Employee not found with id: 999", exception.getMessage());

        // Debugging: Print out the exception
        System.out.println("Exception thrown: " + exception.getMessage());
    }
}
