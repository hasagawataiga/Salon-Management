package com.salon.manager.dto;

import com.salon.manager.model.Service;
import com.salon.manager.model.ServiceCategory;

public record ServiceDTO(
        Long id,
        String name,
        String description,
        Integer durationMinutes,
        Double price,
        ServiceCategory category,
        boolean active
) {
    public ServiceDTO(Service service) {
        this(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getDurationMinutes(),
                service.getPrice(),
                service.getCategory(),
                service.isActive()
        );
    }
}
