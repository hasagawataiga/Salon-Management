package com.salon.manager.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Duration;
import java.util.Set;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private Duration duration;
    private boolean active = true;

    @ManyToMany(mappedBy = "services")
    private Set<Employee> employees;
}