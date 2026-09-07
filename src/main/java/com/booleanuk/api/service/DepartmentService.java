package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.model.Department;
import com.booleanuk.api.repository.DepartmentRepo;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public Optional<Department> getOne(int id) {
        return departmentRepo.findById(id);
    }

    public Department create(Department department) {
        return departmentRepo.save(department);
    }
}