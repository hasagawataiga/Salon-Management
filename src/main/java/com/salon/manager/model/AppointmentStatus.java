package com.salon.manager.model;

public enum AppointmentStatus {
    BOOKED,         // Appointment scheduled
    CONFIRMED,      // Customer confirmed
    IN_PROGRESS,    // Service being performed
    COMPLETED,      // Service finished
    CANCELLED,      // Cancelled by salon/customer
    NO_SHOW         // Customer didn't arrive
}
