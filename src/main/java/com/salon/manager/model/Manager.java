package com.salon.manager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Manager.java
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {
    private String adminLevel;  // Example special field
    private boolean canAudit;
}