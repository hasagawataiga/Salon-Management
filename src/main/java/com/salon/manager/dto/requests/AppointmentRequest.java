package com.salon.manager.dto.requests;

import java.time.LocalDateTime;

public record AppointmentRequest(
        Long customerId,
        Long employeeId,
        Long serviceId,
        LocalDateTime startTime,
        String notes
) {}
