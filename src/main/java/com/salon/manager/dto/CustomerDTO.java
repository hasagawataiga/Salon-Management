package com.salon.manager.dto;

import com.salon.manager.model.Customer;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String allergies,
        String hairType,
        String skinType,
        int loyaltyPoints,
        String preferences
) {
    public CustomerDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAllergies(),
                customer.getHairType(),
                customer.getSkinType(),
                customer.getLoyaltyPoints(),
                customer.getPreferences()
        );
    }
}
