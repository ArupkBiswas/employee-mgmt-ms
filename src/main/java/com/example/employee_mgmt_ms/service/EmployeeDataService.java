package com.example.employee_mgmt_ms.service;

import com.example.employee_mgmt_ms.exception.base.DataNotFoundException;
import com.example.employee_mgmt_ms.model.dto.EmployeeDataEntity;
import com.example.employee_mgmt_ms.model.request.EmployeeDataRequest;
import com.example.employee_mgmt_ms.model.response.EmployeeDataResponse;
import com.example.employee_mgmt_ms.repository.dao.EmployeeDataRepository;
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
     public List<EmployeeDataResponse> getAllEmployees() {
        List <EmployeeDataEntity> allEmployeeDataEntity = employeeDataRepository.findAll();
        // Convert the list of EmployeeDataEntity to EmployeeDataResponse
        return allEmployeeDataEntity.stream().map(employee -> {
            EmployeeDataResponse response = new EmployeeDataResponse();
            response.setId(employee.getId());
            response.setFirstName(employee.getFirstName());
            response.setLastName(employee.getLastName());
            response.setEmail(employee.getEmail());
            response.setPhoneNumber(employee.getPhoneNumber());
            response.setJoiningDate(employee.getJoiningDate());
            response.setJobId(employee.getJobId());
            response.setSalary(BigInteger.valueOf(employee.getSalary().intValue()));
            response.setCommissionPct(employee.getCommissionPct());
            response.setManagerId(employee.getManagerId());
            response.setDepartmentId(employee.getDepartmentId());
            return response;
        }).toList();
     }

    // Example method to get an employee by ID
     public EmployeeDataResponse getEmployeeById(int id) throws DataNotFoundException {
         EmployeeDataEntity employeeDataEntity = employeeDataRepository.findById(id).orElse(null);
         if(employeeDataEntity != null) {
             EmployeeDataResponse response = new EmployeeDataResponse();
             response.setId(employeeDataEntity.getId());
             response.setFirstName(employeeDataEntity.getFirstName());
             response.setLastName(employeeDataEntity.getLastName());
             response.setEmail(employeeDataEntity.getEmail());
             response.setPhoneNumber(employeeDataEntity.getPhoneNumber());
             response.setJoiningDate(employeeDataEntity.getJoiningDate());
             response.setJobId(employeeDataEntity.getJobId());
             response.setSalary(BigInteger.valueOf(employeeDataEntity.getSalary().intValue()));
             response.setCommissionPct(employeeDataEntity.getCommissionPct());
             response.setManagerId(employeeDataEntity.getManagerId());
             response.setDepartmentId(employeeDataEntity.getDepartmentId());
             return response;
         } else {
             throw new DataNotFoundException("Data not found", "Employee with ID " + id + " does not exist.");
         }
     }

     //Example method to add a new employee
    public EmployeeDataResponse addEmployee(EmployeeDataRequest employee) {
        EmployeeDataEntity employeeEntity = setEmployeeData(employee);

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

    // Example method to update an existing employee
    public EmployeeDataResponse updateEmployee(int id, EmployeeDataRequest employee) throws DataNotFoundException {
        // This method will update an existing employee's data
        // It first checks if the employee exists, then updates the data
        if (!checkIfEmployeeExists(id)) {
            throw new DataNotFoundException("Data not found", "Employee with ID " + id + " does not exist.");
        }

        EmployeeDataEntity employeeEntity = setEmployeeData(employee);
        employeeEntity.setId(id); // Set the ID to ensure the correct employee is updated

        // Save the updated entity to the database
        EmployeeDataEntity updatedEmployee = employeeDataRepository.save(employeeEntity);

        // Convert the updated entity back to a response object
        EmployeeDataResponse response = new EmployeeDataResponse();
        response.setId(updatedEmployee.getId());
        response.setFirstName(updatedEmployee.getFirstName());
        response.setEmail(updatedEmployee.getEmail());
        response.setDepartmentId(updatedEmployee.getDepartmentId());

        // Send back the response object
        return response;
    }

    // This method converts the EmployeeDataRequest to EmployeeDataEntity
    private EmployeeDataEntity setEmployeeData(EmployeeDataRequest employee) {
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

    // Example method to delete an employee by ID
    public void deleteEmployee(int id) throws DataNotFoundException {
        // This method will delete an employee by their ID
        // It calls the repository to perform the deletion operation
        if (!checkIfEmployeeExists(id)) {
            throw new DataNotFoundException("Data not found" ,"Employee with ID " + id + " does not exist.");
        } else {
            employeeDataRepository.deleteById(id);
        }

    }

    // This method checks if an employee exists by their ID
    public boolean checkIfEmployeeExists(int id) {
        // This method checks if an employee exists by their ID
        return employeeDataRepository.existsById(id);
    }
}
