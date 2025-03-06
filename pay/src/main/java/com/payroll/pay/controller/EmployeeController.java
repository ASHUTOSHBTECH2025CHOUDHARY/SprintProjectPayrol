package com.payroll.pay.controller;

import com.payroll.pay.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.payroll.pay.entity.Employee;
import com.payroll.pay.repository.EmployeeRepository;
import com.payroll.pay.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // POST - Create a new employee
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // GET - Retrieve all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET - Retrieve single employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT - Update an existing employee
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

    // DELETE - Delete an employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // POST - Create a new employee using DTO
    @PostMapping("/createDTO")
    public ResponseEntity<Employee> createEmployeeDTO(@RequestBody EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    // PUT - Update an existing employee using DTO
    @PutMapping("/updateDTO/{id}")
    public ResponseEntity<Employee> updateEmployeeDTO(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return updatedEmployee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // GET - Retrieve all employees but return DTO list
    @GetMapping("/getAllDTO")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDTO() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesDTO();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    // GET - Retrieve a single employee by ID but return as DTO
    @GetMapping("/getDTO/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDTOById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeDTOById(id);
        return employeeDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //Delete Employee by DTO
    @DeleteMapping("/deleteDTO/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeDTO(@PathVariable Long id) {
        Optional<EmployeeDTO> deletedEmployee = employeeService.deleteEmployeeDTO(id);
        return deletedEmployee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}