package com.api.clinica.domain.appointment.dtos;

import com.api.clinica.domain.appointment.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Integer doctorId,
        Integer patientId,
        AppointmentStatus status,
        LocalDateTime appointmentDate
) {
}
