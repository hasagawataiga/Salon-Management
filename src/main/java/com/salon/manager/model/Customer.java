package com.salon.manager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    private String notes;
    private int loyaltyPoints;
}