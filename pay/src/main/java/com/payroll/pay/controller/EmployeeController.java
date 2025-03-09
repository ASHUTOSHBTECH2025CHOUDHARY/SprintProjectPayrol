package com.payroll.pay.controller;

import com.payroll.pay.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.payroll.pay.entity.Employee;
import com.payroll.pay.repository.EmployeeRepository;
import com.payroll.pay.dto.EmployeeDTO;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee updatedEmployee = employee.get();
            updatedEmployee.setName(employeeDetails.getName());
            updatedEmployee.setSalary(employeeDetails.getSalary());
            Employee savedEmployee = employeeRepository.save(updatedEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createDTO")
    public ResponseEntity<Employee> createEmployeeDTO(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/updateDTO/{id}")
    public ResponseEntity<Employee> updateEmployeeDTO(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/getAllDTO")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDTO() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesDTO();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/getDTO/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDTOById(@PathVariable Long id) {
        log.info("Fetching employee DTO with ID: {}", id);
        EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(id);
        log.info("Successfully fetched employee DTO with ID: {}", id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDTO/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeDTO(@PathVariable Long id) {
        log.info("Deleting employee DTO with ID: {}", id);
        EmployeeDTO deletedEmployee = employeeService.deleteEmployeeDTO(id);
        log.info("Successfully deleted employee DTO with ID: {}", id);
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }
}