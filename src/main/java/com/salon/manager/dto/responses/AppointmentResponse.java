package com.salon.manager.dto.responses;

import com.salon.manager.model.Appointment;
import com.salon.manager.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long customerId,
        String customerName,
        Long employeeId,
        String employeeName,
        Long serviceId,
        String serviceName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        AppointmentStatus status,
        String notes
) {
    public AppointmentResponse(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getCustomer().getId(),
                appointment.getCustomer().getFirstName() + " " + appointment.getCustomer().getLastName(),
                appointment.getEmployee().getId(),
                appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName(),
                appointment.getService().getId(),
                appointment.getService().getName(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus(),
                appointment.getNotes()
        );
    }
}