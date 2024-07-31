package com.api.clinica.domain.appointment.dtos;

public record AppointmentSearchDTO(
        String patientName,
        Integer patientId,
        Integer doctorId,
        String month,
        Integer year
) {
}
