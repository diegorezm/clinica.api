package com.api.clinica.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Integer doctorId,
        Integer patientId,
        AppointmentStatus status,
        LocalDateTime appointmentDate
) {
}
