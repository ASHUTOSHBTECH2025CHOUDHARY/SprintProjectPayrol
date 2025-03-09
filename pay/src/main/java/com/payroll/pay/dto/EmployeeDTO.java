package com.payroll.pay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {
    @NotBlank(message = "Name is required and cannot be empty")
    @Pattern(regexp = "^[A-Za-z\\s]{2,50}$", message = "Name must be 2-50 characters long and contain only letters and spaces")
    private String name;
    private Double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getSalary() { return salary; }

    public void setSalary(Double salary) { this.salary = salary; }
}