package com.example.spring_craft_ms.controller;

import com.example.spring_craft_ms.exception.base.DataNotFoundException;
import com.example.spring_craft_ms.model.dto.EmployeeDataEntity;
import com.example.spring_craft_ms.model.request.EmployeeDataRequest;
import com.example.spring_craft_ms.model.response.EmployeeDataResponse;
import com.example.spring_craft_ms.service.EmployeeDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
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
     @GetMapping("/all")
     public List<EmployeeDataEntity> getAllEmployees() {
         // This method will return a list of all employees
         // It calls the service layer to fetch the data
         return employeeService.getAllEmployees();
     }

    // Example method to get an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDataEntity> getEmployeeById(@PathVariable int id) {
        // This method will return an employee by their ID
        // It calls the service layer to fetch the data
        EmployeeDataEntity employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


     //Example method to add a new employee
     @PostMapping("/add")
     public EmployeeDataResponse addEmployee(@RequestBody EmployeeDataRequest employee) {
         return employeeService.addEmployee(employee);
     }

     // Example method to update an employee
//    @PutMapping("/update/{id}")
//    public EmployeeDataResponse updateEmployee(@PathVariable int id, @RequestBody EmployeeDataRequest employee) {
//        // This method will update an existing employee's data
//        // It calls the service layer to perform the update operation
//        return employeeService.updateEmployee(id, employee);
//    }

    // Example method to delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) throws DataNotFoundException {
        // This method will delete an employee by their ID
        // It calls the service layer to perform the deletion operation
        employeeService.deleteEmployee(id);
        // Return a response indicating the deletion was successful
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
