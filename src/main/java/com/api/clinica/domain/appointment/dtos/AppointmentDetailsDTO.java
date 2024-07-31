package com.api.clinica.domain.appointment.dtos;

import com.api.clinica.domain.appointment.Appointment;
import com.api.clinica.domain.appointment.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentDetailsDTO {
    private Integer appointmentId;
    private String patientName;
    private String doctorName;
    private Integer patientId;
    private Integer doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

    public AppointmentDetailsDTO(Appointment payload, String patientName, String doctorName) {
        this.appointmentId = payload.getId();
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.patientId = payload.getPatient().getId();
        this.doctorId = payload.getDoctor().getId();
        this.appointmentDate = payload.getAppointmentDate();
        this.status = payload.getStatus();
    }
}
