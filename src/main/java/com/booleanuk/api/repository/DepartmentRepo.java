package com.booleanuk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booleanuk.api.model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}