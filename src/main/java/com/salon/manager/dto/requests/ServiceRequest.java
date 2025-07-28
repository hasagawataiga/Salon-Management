package com.salon.manager.dto.requests;

import com.salon.manager.model.ServiceCategory;

public record ServiceRequest(
        String name,
        String description,
        Integer durationMinutes,
        Double price,
        ServiceCategory category,
        boolean active
) {}
