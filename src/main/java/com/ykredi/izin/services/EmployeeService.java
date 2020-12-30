package com.ykredi.izin.services;

import com.ykredi.izin.objects.entities.Employee;
import com.ykredi.izin.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    final private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(UUID employeeId) {
        return employeeRepository.findById(employeeId);
    }

}
