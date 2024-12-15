package com.example.demo.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create a new employee
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Update an employee
    public void updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = getEmployeeById(id);
        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());
        employeeRepository.save(employee);
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

 // Generate attendance report
    public List<Map<String, Object>> generateAttendanceReport() {
        return employeeRepository.findAll().stream()
                .map(emp -> {
                    Map<String, Object> report = new HashMap<>();
                    report.put("Employee", emp.getName());
                    report.put("AttendanceDays", 20); // Replace with actual attendance logic if available
                    return report;
                })
                .collect(Collectors.toList());
    }


 // Generate salary report
    public List<Map<String, Object>> generateSalaryReport() {
        return employeeRepository.findAll().stream()
                .map(emp -> {
                    Map<String, Object> report = new HashMap<>();
                    report.put("Employee", emp.getName());
                    report.put("Salary", emp.getSalary());
                    return report;
                })
                .collect(Collectors.toList());
    }

    // Generate department distribution report
    public Map<String, Long> generateDepartmentDistribution() {
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }
}
