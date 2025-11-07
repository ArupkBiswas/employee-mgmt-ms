package com.example.employee_mgmt_ms.repository.dao;

import com.example.employee_mgmt_ms.model.dto.EmployeeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDataRepository extends JpaRepository<EmployeeDataEntity, Integer> {
    List<EmployeeDataEntity> findAll();
    Optional<EmployeeDataEntity> findByUsername(String username);

}
