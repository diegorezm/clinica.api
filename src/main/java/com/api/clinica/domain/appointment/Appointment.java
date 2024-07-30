package com.api.clinica.domain.appointment;

import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    public static Appointment createMockAppointment(Patient patient, Doctor doctor) {
        Faker faker = new Faker();
        AppointmentStatus randomStatus = getRandomStatus();
        LocalDateTime appointmentDate = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return new Appointment(faker.idNumber().hashCode(), randomStatus, appointmentDate, LocalDateTime.now(), LocalDateTime.now(), patient, doctor);
    }

    private static AppointmentStatus getRandomStatus() {
        AppointmentStatus[] statuses = AppointmentStatus.values();
        int randomIndex = new Random().nextInt(statuses.length);
        return statuses[randomIndex];
    }

    @Override
    public String toString() {
        return "id:" + this.id;
    }
}
