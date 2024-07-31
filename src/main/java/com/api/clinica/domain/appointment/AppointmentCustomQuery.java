package com.api.clinica.domain.appointment;

import com.api.clinica.domain.appointment.dtos.AppointmentDetailsDTO;
import com.api.clinica.domain.appointment.dtos.AppointmentSearchDTO;
import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentCustomQuery {
    private final EntityManager em;
    private final CriteriaBuilder cb = em.getCriteriaBuilder();

    public List<AppointmentDetailsDTO> findAppointmentsByDoctorIdAndPatientNameAndMonth(AppointmentSearchDTO payload) {
        CriteriaQuery<AppointmentDetailsDTO> query = cb.createQuery(AppointmentDetailsDTO.class);
        Root<Appointment> appointmentRoot = query.from(Appointment.class);

        Join<Appointment, Doctor> doctorJoin = appointmentRoot.join("doctor");
        Join<Appointment, Patient> patientJoin = appointmentRoot.join("patient");

        Predicate doctorPredicate = cb.equal(doctorJoin.get("id"), payload.doctorId());
        Predicate patientNamePredicate = cb.like(cb.lower(patientJoin.get("name")), "%" + payload.patientName().toLowerCase() + "%");
        Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, appointmentRoot.get("appointmentDate")), payload.month());

        query.select(cb.construct(AppointmentDetailsDTO.class,
                appointmentRoot,
                patientJoin.get("name"),
                doctorJoin.get("user").get("name")
        )).where(cb.and(doctorPredicate, patientNamePredicate, monthPredicate));

        TypedQuery<AppointmentDetailsDTO> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    public List<AppointmentDetailsDTO> findAppointmentsByDoctorIdAndMonth(AppointmentSearchDTO payload) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AppointmentDetailsDTO> query = cb.createQuery(AppointmentDetailsDTO.class);
        Root<Appointment> appointmentRoot = query.from(Appointment.class);

        Join<Appointment, Doctor> doctorJoin = appointmentRoot.join("doctor");
        Join<Appointment, Patient> patientJoin = appointmentRoot.join("patient");

        Predicate doctorPredicate = cb.equal(doctorJoin.get("id"), payload.doctorId());
        Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, appointmentRoot.get("appointmentDate")), payload.month());

        query.select(cb.construct(AppointmentDetailsDTO.class,
                appointmentRoot.get("id"),
                appointmentRoot.get("status"),
                appointmentRoot.get("appointmentDate"),
                patientJoin.get("name"),
                doctorJoin.get("user").get("name")
        )).where(cb.and(doctorPredicate, monthPredicate));

        TypedQuery<AppointmentDetailsDTO> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    public List<AppointmentDetailsDTO> findAppointmentsByPatientIdAndMonth(AppointmentSearchDTO payload) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AppointmentDetailsDTO> query = cb.createQuery(AppointmentDetailsDTO.class);
        Root<Appointment> appointmentRoot = query.from(Appointment.class);

        Join<Appointment, Doctor> doctorJoin = appointmentRoot.join("doctor");
        Join<Appointment, Patient> patientJoin = appointmentRoot.join("patient");

        Predicate patientPredicate = cb.equal(patientJoin.get("id"), payload.patientId());
        Predicate monthPredicate = cb.equal(cb.function("MONTH", Integer.class, appointmentRoot.get("appointmentDate")), payload.month());

        query.select(cb.construct(AppointmentDetailsDTO.class,
                appointmentRoot.get("id"),
                appointmentRoot.get("status"),
                appointmentRoot.get("appointmentDate"),
                patientJoin.get("name"),
                doctorJoin.get("user").get("name")
        )).where(cb.and(patientPredicate, monthPredicate));

        TypedQuery<AppointmentDetailsDTO> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
