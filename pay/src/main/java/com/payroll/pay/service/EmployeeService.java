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

    // Get all Employees and return as DTO list
    public List<EmployeeDTO> getAllEmployeesDTO() {
        return employeeRepository.findAll().stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    // Get Employee by ID and return as DTO
    public Optional<EmployeeDTO> getEmployeeDTOById(Long id) {
        return employeeRepository.findById(id)
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()));
    }

    // Update Employee using DTO
    public Optional<Employee> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSalary(employeeDTO.getSalary());
            return employeeRepository.save(existingEmployee);
        });
    }

    // Delete Employee and return deleted Employee as DTO
    public Optional<EmployeeDTO> deleteEmployeeDTO(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(employeeRepository::delete);
        return employee.map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()));
    }
}
