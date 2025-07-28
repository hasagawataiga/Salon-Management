package com.salon.manager.dto;

import com.salon.manager.model.User;
import com.salon.manager.model.UserRole;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        UserRole role,
        boolean active,
        LocalDateTime registeredDate
) {
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.isActive(),
                user.getRegisteredDate()
        );
    }
}
