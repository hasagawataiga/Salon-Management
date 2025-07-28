package com.salon.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("MANAGER")
@Getter
public class Manager extends User {

    @Column(length = 50)
    private String managementLevel;  // Senior, Junior, etc.

    @Column(nullable = false)
    private boolean inventoryAccess;

    @Column(nullable = false)
    private boolean financialAccess;

    @Column(nullable = false )
    private boolean canAudit;
}