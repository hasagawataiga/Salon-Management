package com.salon.manager.controller;

import com.salon.manager.dto.requests.AppointmentRequest;
import com.salon.manager.dto.responses.AppointmentResponse;
import com.salon.manager.model.*;
import com.salon.manager.repository.AppointmentRepository;
import com.salon.manager.repository.ServiceRepository;
import com.salon.manager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Validated @RequestBody AppointmentRequest request) {

        Customer customer = userRepository.findById(request.customerId())
                .filter(Customer.class::isInstance)
                .map(Customer.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Employee employee = userRepository.findById(request.employeeId())
                .filter(Employee.class::isInstance)
                .map(Employee.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Service service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setEmployee(employee);
        appointment.setService(service);
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.startTime().plusMinutes(service.getDurationMinutes()));
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setNotes(request.notes());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AppointmentResponse(savedAppointment));
    }
}