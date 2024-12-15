package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;

@Service
public class UserService {

    // Simulate a list of users (in a real scenario, this would be a database)
    private List<Employee> employeeDatabase = List.of(
        new Employee(),
        new Employee(),
        new Employee(),
        new Employee()
    );

    /**
     * Fetches an employee by their ID from the mock database.
     * @param id The ID of the employee to fetch.
     * @return The employee details, or null if not found.
     */
    public Employee getUserById(Long id) {
        // Use Optional to handle the case where the employee might not be found
        Optional<Employee> employee = employeeDatabase.stream()
            .filter(emp -> emp.getId().equals(id))
            .findFirst();

        // If found, return the employee; otherwise, return null or handle the case as needed
        return employee.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}