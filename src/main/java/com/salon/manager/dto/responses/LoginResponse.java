package com.salon.manager.dto.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public record LoginResponse(
        Long id,
        String email,
        String accessToken,
        String tokenType,
        long expiresIn,
        String role
) {}
