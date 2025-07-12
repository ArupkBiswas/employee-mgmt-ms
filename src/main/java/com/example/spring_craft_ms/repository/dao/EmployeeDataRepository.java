package com.example.spring_craft_ms.repository.dao;

import com.example.spring_craft_ms.model.dto.EmployeeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDataRepository extends JpaRepository<EmployeeDataEntity, Integer> {
    List<EmployeeDataEntity> findAll();
}
