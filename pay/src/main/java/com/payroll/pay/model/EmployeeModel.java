package com.payroll.pay.model;

import com.payroll.pay.dto.EmployeeDTO;

public class EmployeeModel {
    private String name;
    private Double salary;

    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }

    public String getName() { return name; }

    public Double getSalary() { return salary; }
}
