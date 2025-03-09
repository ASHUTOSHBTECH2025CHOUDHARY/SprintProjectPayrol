package com.payroll.pay.service;

import com.payroll.pay.dto.EmployeeDTO;
import com.payroll.pay.entity.Employee;
import com.payroll.pay.exception.EmployeeNotFoundException;
import com.payroll.pay.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        log.info("Saving new employee: {}", employeeDTO);
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getAllEmployeesDTO() {
        log.info("Fetching all employees as DTO");
        return employeeRepository.findAll().stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeDTOById(Long id) {
        log.info("Fetching employee DTO with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSalary(employeeDTO.getSalary());
        Employee savedEmployee = employeeRepository.save(existingEmployee);
        log.info("Employee with ID {} updated successfully", id);
        return savedEmployee;
    }

    public EmployeeDTO deleteEmployeeDTO(Long id) {
        log.info("Deleting employee DTO with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employeeRepository.delete(employee);
        log.info("Employee with ID {} deleted successfully", id);
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }
}