package com.salon.manager.dto;

import com.salon.manager.model.Employee;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record EmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String specialization,
        LocalDate hireDate,
        Double salary,
        Set<ServiceDTO> services
) {
    public EmployeeDTO(Employee employee) {
        this(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getSpecialization(),
                employee.getHireDate(),
                employee.getSalary(),
                employee.getServices().stream()
                        .map(ServiceDTO::new)
                        .collect(Collectors.toSet())
        );
    }
}
