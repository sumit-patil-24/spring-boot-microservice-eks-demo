package com.example.employee_service.services;

import com.example.employee_service.models.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        Employee createEmployee = new Employee();
        createEmployee.setName(employee.getName());
        createEmployee.setPosition(employee.getPosition());
        return employeeRepository.save(createEmployee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}
