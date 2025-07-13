package com.example.spring_craft_ms.service;

import com.example.spring_craft_ms.exception.base.DataNotFoundException;
import com.example.spring_craft_ms.model.dto.EmployeeDataEntity;
import com.example.spring_craft_ms.model.request.EmployeeDataRequest;
import com.example.spring_craft_ms.model.response.EmployeeDataResponse;
import com.example.spring_craft_ms.repository.dao.EmployeeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class EmployeeDataService {

    @Autowired
    private EmployeeDataRepository employeeDataRepository;
    // This service will handle business logic related to employee data
    // For example, methods to create, update, delete, and retrieve employee data
    // You can inject the EmployeeDataRepository here to interact with the database

     //Example of a method to get all employees:
     public List<EmployeeDataEntity> getAllEmployees() {
         return employeeDataRepository.findAll();
     }

    // Example method to get an employee by ID
     public EmployeeDataEntity getEmployeeById(int id) {
         return employeeDataRepository.findById(id).orElse(null);
     }

     //Example method to add a new employee
    public EmployeeDataResponse addEmployee(EmployeeDataRequest employee) {
        EmployeeDataEntity employeeEntity = getEmployeeData(employee);

        // Save the entity to the database
        EmployeeDataEntity savedEmployee = employeeDataRepository.save(employeeEntity);

        // Convert the saved entity back to a request object
        EmployeeDataResponse response = new EmployeeDataResponse();
        response.setId(savedEmployee.getId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setEmail(savedEmployee.getEmail());
        response.setDepartmentId(savedEmployee.getDepartmentId());

        //Send back the response object
        return response;
    }

    // This method converts the EmployeeDataRequest to EmployeeDataEntity
    private EmployeeDataEntity getEmployeeData(EmployeeDataRequest employee) {
        EmployeeDataEntity employeeEntity = new EmployeeDataEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setPhoneNumber(employee.getPhoneNumber());
        employeeEntity.setJoiningDate(employee.getJoiningDate());
        employeeEntity.setJobId(employee.getJobId());
        employeeEntity.setSalary(BigInteger.valueOf(employee.getSalary()));
        employeeEntity.setCommissionPct(employee.getCommissionPct());
        employeeEntity.setManagerId(employee.getManagerId());
        employeeEntity.setDepartmentId(employee.getDepartmentId());
        return employeeEntity;
    }

    public void deleteEmployee(int id) throws DataNotFoundException {
        // This method will delete an employee by their ID
        // It calls the repository to perform the deletion operation
        if (!checkIfEmployeeExists(id)) {
            throw new DataNotFoundException("Data not found" ,"Employee with ID " + id + " does not exist.");
        } else {
            employeeDataRepository.deleteById(id);
        }

    }

    public boolean checkIfEmployeeExists(int id) {
        // This method checks if an employee exists by their ID
        return employeeDataRepository.existsById(id);
    }
}
