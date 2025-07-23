package com.salon.manager.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
    private String specialization;
    private LocalDate hireDate;
    private double salary;

    @ManyToMany
    @JoinTable(
            name = "employee_service",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services;
}