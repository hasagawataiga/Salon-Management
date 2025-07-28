package com.salon.manager.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@DiscriminatorValue("EMPLOYEE")
@EqualsAndHashCode(callSuper = true)
@Getter
public class Employee extends User {
    private String specialization;   // Hair, Nails, Skin, etc.
    private LocalDate hireDate;

    @Column(nullable = true)
    private Double salary;  // Nullable for new hires

    @ManyToMany
    @JoinTable(
            name = "employee_service",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services;  // Services this employee can perform
}