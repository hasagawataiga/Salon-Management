package com.salon.manager.repository;

import com.salon.manager.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByEmployeeIdAndStartTimeBetween(
            Long employeeId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByCustomerId(Long customerId);
}
