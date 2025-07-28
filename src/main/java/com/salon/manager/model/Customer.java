package com.salon.manager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
public class Customer extends User {
    private String allergies;        // Important health notes
    private String hairType;         // For personalized recommendations
    private String skinType;         // For facial treatments
    private String nailType;         // For facial treatments
    private int loyaltyPoints;
    private String preferences;      // Stylist preferences, service preferences
}