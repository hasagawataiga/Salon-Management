package com.salon.manager.dto;

import com.salon.manager.model.Appointment;
import com.salon.manager.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        CustomerDTO customer,
        EmployeeDTO employee,
        ServiceDTO service,
        LocalDateTime startTime,
        LocalDateTime endTime,
        AppointmentStatus status,
        String notes
) {
    public AppointmentDTO(Appointment appointment) {
        this(
                appointment.getId(),
                new CustomerDTO(appointment.getCustomer()),
                new EmployeeDTO(appointment.getEmployee()),
                new ServiceDTO(appointment.getService()),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus(),
                appointment.getNotes()
        );
    }
}