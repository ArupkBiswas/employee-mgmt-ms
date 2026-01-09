package com.example.employee_mgmt_ms.controller;

import com.example.employee_mgmt_ms.exception.base.DataNotFoundException;
import com.example.employee_mgmt_ms.model.request.EmployeeDataRequest;
import com.example.employee_mgmt_ms.model.response.EmployeeDataResponse;
import com.example.employee_mgmt_ms.security.CurrentUser;
import com.example.employee_mgmt_ms.service.EmployeeDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeDataController {
    private static EmployeeDataService employeeService;
    // Autowiring the EmployeeDataService to handle business logic
    public EmployeeDataController(EmployeeDataService employeeService) {
        EmployeeDataController.employeeService = employeeService;
    }
    // This controller will handle employee-related requests
    // You can define methods here to handle CRUD operations for employee data

    // Example method to get all employees
     @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
     public List<EmployeeDataResponse> getAllEmployees() {  //@CurrentUser String currentUser
         // This method will return a list of all employees
         // It calls the service layer to fetch the data
         return employeeService.getAllEmployees();
     }

    // Example method to get an employee by ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDataResponse> getEmployeeById(@PathVariable int id) throws DataNotFoundException {
        // This method will return an employee by their ID
        // It calls the service layer to fetch the data
        EmployeeDataResponse employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


     //Example method to add a new employee
     @PostMapping("/add")
     public ResponseEntity<EmployeeDataResponse> addEmployee(@RequestBody EmployeeDataRequest employee) {
            // This method will add a new employee
            // It calls the service layer to perform the addition operation
            EmployeeDataResponse response = employeeService.addEmployee(employee);
            // Return a response entity with the created employee data
            return ResponseEntity.ok(response);
     }

     // Example method to update an employee
    @PatchMapping("/update/{id}")
    public ResponseEntity<EmployeeDataResponse> updateEmployee(@PathVariable int id, @RequestBody EmployeeDataRequest employee) throws DataNotFoundException {
        // This method will update an existing employee's data
        // It calls the service layer to perform the update operation
        EmployeeDataResponse dataResponse = employeeService.updateEmployee(id, employee);

        // Return a response entity with the updated employee data
        return ResponseEntity.ok(dataResponse);
    }

    // Example method to delete an employee
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) throws DataNotFoundException {
        // This method will delete an employee by their ID
        // It calls the service layer to perform the deletion operation
        employeeService.deleteEmployee(id);
        // Return a response indicating the deletion was successful
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }
}
