package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create a new employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee created successfully.");
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee updated successfully.");
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    // API to get attendance report
    @GetMapping("/reports/attendance")
    public ResponseEntity<List<Map<String, Object>>> getAttendanceReport() {
        List<Map<String, Object>> attendanceReport = employeeService.generateAttendanceReport();
        return ResponseEntity.ok(attendanceReport);
    }

    // API to get salary report
    @GetMapping("/reports/salary")
    public ResponseEntity<List<Map<String, Object>>> getSalaryReport() {
        List<Map<String, Object>> salaryReport = employeeService.generateSalaryReport();
        return ResponseEntity.ok(salaryReport);
    }

    // API to get department-wise distribution
    @GetMapping("/reports/department-distribution")
    public ResponseEntity<Map<String, Long>> getDepartmentDistribution() {
        Map<String, Long> departmentDistribution = employeeService.generateDepartmentDistribution();
        return ResponseEntity.ok(departmentDistribution);
    }
}
