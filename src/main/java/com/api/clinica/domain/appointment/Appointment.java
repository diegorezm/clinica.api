package com.api.clinica.domain.appointment;

import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "appointments")
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    private LocalDateTime appointmentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public Appointment(AppointmentDTO payload, Doctor doctor, Patient patient) {
        this.status = payload.status();
        this.appointmentDate = payload.appointmentDate();
        this.doctor = doctor;
        this.patient = patient;
    }
}
