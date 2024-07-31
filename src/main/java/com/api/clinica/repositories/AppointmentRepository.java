package com.api.clinica.repositories;

import com.api.clinica.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a " +
            "WHERE LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND a.doctor.id = :doctorId " +
            "AND FUNCTION('MONTH', a.appointmentDate) = :month " +
            "AND FUNCTION('YEAR', a.appointmentDate) = :year")
    List<Appointment> findAppointmentsByPatientNameAndDoctorIdAndMonth(
            @Param("name") String name,
            @Param("doctorId") Integer doctorId,
            @Param("month") int month,
            @Param("year") int year);

    @Query("SELECT a FROM Appointment a " +
            "WHERE LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND FUNCTION('MONTH', a.appointmentDate) = :month " +
            "AND FUNCTION('YEAR', a.appointmentDate) = :year")
    List<Appointment> findAppointmentsByPatientNameAndMonth(
            @Param("name") String name,
            @Param("month") int month,
            @Param("year") int year);
}

