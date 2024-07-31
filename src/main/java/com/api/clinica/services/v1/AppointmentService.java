package com.api.clinica.services.v1;

import com.api.clinica.domain.appointment.Appointment;
import com.api.clinica.domain.appointment.AppointmentCustomQuery;
import com.api.clinica.domain.appointment.dtos.AppointmentDTO;
import com.api.clinica.domain.appointment.dtos.AppointmentDetailsDTO;
import com.api.clinica.domain.appointment.dtos.AppointmentSearchDTO;
import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import com.api.clinica.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService{
    private final AppointmentCustomQuery appointmentCustomQuery;
    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentDetailsDTO getByIdWithDetails(Integer id) {
        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow();
        Doctor doctor = this.doctorService.getById(appointment.getDoctor().getId());
        Patient patient = this.patientService.getById(appointment.getPatient().getId());
        return new AppointmentDetailsDTO(appointment, patient.getName(), doctor.getUser().getName());
    }

    public Appointment getById(Integer id) {
        return this.appointmentRepository.findById(id).orElseThrow();
    }

    public List<AppointmentDetailsDTO> getByDoctorId(AppointmentSearchDTO payload) {
        return this.appointmentCustomQuery.findAppointmentsByDoctorIdAndMonth(payload);
    }

    public List<AppointmentDetailsDTO> getByPatientId(AppointmentSearchDTO payload) {
        return this.appointmentCustomQuery.findAppointmentsByPatientIdAndMonth(payload);
    }

    public List<AppointmentDetailsDTO> getByPatientNameAndDoctorId(AppointmentSearchDTO payload) {
        return this.appointmentCustomQuery.findAppointmentsByDoctorIdAndPatientNameAndMonth(payload);
    }

    public Appointment create(AppointmentDTO payload) {
        Doctor doctor = this.doctorService.getById(payload.doctorId());
        Patient patient = this.patientService.getById(payload.patientId());
        return this.appointmentRepository.save(new Appointment(payload, doctor, patient));
    }

    public Appointment update(AppointmentDTO payload, Integer id) {
        Appointment appointment = this.getById(id);
        appointment.setAppointmentDate(payload.appointmentDate());
        appointment.setStatus(payload.status());
        return appointment;
    }

    public void bulkInsert(List<AppointmentDTO> paayload, Integer doctorId, Integer patientId) {
        int batchSize = 30;
        int total = paayload.size();
        Patient patient = this.patientService.getById(patientId);
        Doctor doctor = this.doctorService.getById(doctorId);
        List<Appointment> appointments = paayload
                .stream()
                .map(e -> new Appointment(e, doctor, patient))
                .toList();

        for (int i = 0; i < total; i += batchSize) {
            int end = Math.min(i + batchSize, total);
            appointmentRepository.saveAll(appointments.subList(i, end));
        }
    }
    public void delete(Integer id) {
        this.appointmentRepository.delete(this.getById(id));
    }
}

