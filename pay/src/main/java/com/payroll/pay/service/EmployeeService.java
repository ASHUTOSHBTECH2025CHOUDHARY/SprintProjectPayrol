package com.payroll.pay.service;

import com.payroll.pay.dto.EmployeeDTO;
import com.payroll.pay.entity.Employee;
import com.payroll.pay.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create Employee using DTO
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }
}
