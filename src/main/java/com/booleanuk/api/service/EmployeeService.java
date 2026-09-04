package com.booleanuk.api.service;

import com.booleanuk.api.model.Employee;
import com.booleanuk.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> getById(Integer id) {
        return this.employeeRepository.findById(id);
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Optional<Employee> update(Integer id, Employee replacement) {
        return this.employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(replacement.getFirstName());
                    employee.setLastName(replacement.getLastName());
                    employee.setLocation(replacement.getLocation());
                    employee.setEmail(replacement.getEmail());
                    return this.employeeRepository.save(employee);
                });
    }

    public Optional<Employee> delete(Integer id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if (employee.isPresent()) {
            this.employeeRepository.delete(employee.get());
        }
        return employee;
    }
}