Employee Management System (EMS) Documentation
Overview
The Employee Management System (EMS) allows managing employee data with functionalities like adding, updating, viewing, deleting employees, and generating reports. This system implements RESTful APIs using Spring Boot, integrates with a relational database (e.g., MySQL), and uses Spring Security for authentication and authorization. It also supports basic reporting on employee attendance, salary, and department distribution.

1. Employee Module API Endpoints
1.1. Create Employee
Endpoint: /api/employees
Method: POST
Description: Adds a new employee.
Request Body:
json
Copy code
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "1980-01-01",
  "department": "Engineering",
  "salary": 60000
}
Response:
Success:
json
Copy code
{
  "message": "Employee created successfully."
}
Failure (Validation Error):
json
Copy code
{
  "error": "Invalid input data."
}
1.2. Get All Employees
Endpoint: /api/employees
Method: GET
Description: Retrieves a list of all employees.
Response:
Success:
json
Copy code
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "dateOfBirth": "1980-01-01",
    "department": "Engineering",
    "salary": 60000
  }
]
1.3. Get Employee by ID
Endpoint: /api/employees/{id}
Method: GET
Description: Retrieves an employee by ID.
Response:
Success:
json
Copy code
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "1980-01-01",
  "department": "Engineering",
  "salary": 60000
}
Failure (Employee not found):
json
Copy code
{
  "error": "Employee not found."
}
1.4. Update Employee
Endpoint: /api/employees/{id}
Method: PUT
Description: Updates an employee’s details.
Request Body:
json
Copy code
{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "dateOfBirth": "1981-01-01",
  "department": "HR",
  "salary": 65000
}
Response:
Success:
json
Copy code
{
  "message": "Employee updated successfully."
}
1.5. Delete Employee
Endpoint: /api/employees/{id}
Method: DELETE
Description: Deletes an employee by ID.
Response:
Success:
json
Copy code
{
  "message": "Employee deleted successfully."
}
1.6. Generate Attendance Report
Endpoint: /api/employees/reports/attendance
Method: GET
Description: Retrieves the employee attendance report.
Response:
Success:
json
Copy code
[
  {
    "employeeId": 1,
    "name": "John Doe",
    "attendance": "Present"
  }
]
1.7. Generate Salary Report
Endpoint: /api/employees/reports/salary
Method: GET
Description: Retrieves the employee salary report.
Response:
Success:
json
Copy code
[
  {
    "employeeId": 1,
    "name": "John Doe",
    "salary": 60000
  }
]
1.8. Generate Department Distribution Report
Endpoint: /api/employees/reports/department-distribution
Method: GET
Description: Retrieves a department-wise distribution of employees.
Response:
Success:
json
Copy code
{
  "Engineering": 10,
  "HR": 5
}
2. Authentication & Authorization
Basic Authentication is implemented using Spring Security.
Admin users can perform CRUD operations (create, update, delete employees).
Regular users can only perform read operations (view employees).
Spring Security Configuration
Implement basic authentication by setting up UserDetailsService and PasswordEncoder.
The SecurityConfig.java class is used for security configuration.
Example User Roles:
Admin: Has access to all employee management endpoints.
User: Can only access GET endpoints to view employee details.
3. Database Integration
The application uses a relational database (MySQL or PostgreSQL) to store employee data.

Database Configuration
Configure your database connection in application.properties:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=root_password
spring.jpa.hibernate.ddl-auto=update
SQL Queries
Employees are stored in the employee table.
A sample SQL query to create the employee table:
sql
Copy code
CREATE TABLE employee (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  date_of_birth DATE NOT NULL,
  department VARCHAR(50) NOT NULL,
  salary DOUBLE NOT NULL
);
4. Unit Testing
Unit testing is essential to ensure API correctness.

JUnit Test Cases
Test CRUD operations:
Create a mock employee and check the success of creating an employee.
Check if the GET endpoint returns the correct employee by ID.
Test for successful employee updates and deletion.
Mocking services using Mockito to simulate service layer behavior.
Example of a unit test for the createEmployee() API:

java
Copy code
@Test
public void testCreateEmployee() throws Exception {
    Employee employee = new Employee();
    employee.setName("John Doe");
    employee.setEmail("john.doe@example.com");
    employee.setDateOfBirth(Date.valueOf("1980-01-01"));
    employee.setDepartment("Engineering");
    employee.setSalary(60000.0);

    Mockito.when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(employee);

    mockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(employee)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Employee created successfully."));
}
5. How to Run the Project
Step 1: Clone the Repository
Clone the repository:

bash
Copy code
git clone https://github.com/your-repository/employee-management-system.git
cd employee-management-system
Step 2: Install Dependencies
Ensure that Java, Maven, and MySQL/PostgreSQL are installed on your machine. Run the following command to install dependencies:

bash
Copy code
mvn clean install
Step 3: Configure Database
Update the application.properties file with your database details.

Step 4: Run the Application
To run the application, use:

bash
Copy code
mvn spring-boot:run
The application will be accessible at http://localhost:8080.

6. Testing the APIs
Using Postman
Create a new request in Postman for each API endpoint (e.g., GET, POST, PUT, DELETE).
Set the body for POST and PUT requests to include the necessary employee data.
Using Swagger
If Swagger is configured, access the Swagger UI at:

bash
Copy code
http://localhost:8080/swagger-ui.html
Swagger provides an interactive interface to explore and test all API endpoints.

Conclusion
This documentation provides the details on how to use the Employee Management System (EMS) RESTful APIs, integrate the system with a relational database, and implement authentication and authorization. Follow the instructions to run the project and test the APIs using Postman or Swagger. Ensure that the APIs are properly tested before submission using unit tests and Postman.
