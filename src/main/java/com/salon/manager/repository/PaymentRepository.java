package com.salon.manager.repository;

import com.salon.manager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAppointmentCustomerId(Long customerId);
}
