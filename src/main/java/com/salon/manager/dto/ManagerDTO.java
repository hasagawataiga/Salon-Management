package com.salon.manager.dto;

import com.salon.manager.model.Manager;

public record ManagerDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String managementLevel,
        boolean inventoryAccess,
        boolean financialAccess
) {
    public ManagerDTO(Manager manager) {
        this(
                manager.getId(),
                manager.getFirstName(),
                manager.getLastName(),
                manager.getEmail(),
                manager.getPhone(),
                manager.getManagementLevel(),
                manager.isInventoryAccess(),
                manager.isFinancialAccess()
        );
    }
}