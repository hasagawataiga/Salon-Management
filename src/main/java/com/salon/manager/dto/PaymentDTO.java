package com.salon.manager.dto;

import com.salon.manager.model.Payment;
import com.salon.manager.model.PaymentMethod;
import com.salon.manager.model.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentDTO(
        Long id,
        Double amount,
        PaymentMethod method,
        PaymentStatus status,
        String transactionId,
        LocalDateTime paymentDate
) {
    public PaymentDTO(Payment payment) {
        this(
                payment.getId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getPaymentDate()
        );
    }
}
