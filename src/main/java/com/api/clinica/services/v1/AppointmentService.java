package com.api.clinica.services.v1;

import com.api.clinica.domain.appointment.Appointment;
import com.api.clinica.domain.appointment.AppointmentDTO;
import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import com.api.clinica.repositories.AppointmentRepository;
import com.api.clinica.services.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService implements ServiceInterface<Appointment, AppointmentDTO> {
    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Override
    public List<Appointment> getAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment getById(Integer id) {
        return this.appointmentRepository.findById(id).orElseThrow();
    }

    @Override
    public Appointment create(AppointmentDTO payload) {
        Doctor doctor = this.doctorService.getById(payload.doctorId());
        Patient patient = this.patientService.getById(payload.patientId());
        return this.appointmentRepository.save(new Appointment(payload, doctor, patient));
    }

    @Override
    public Appointment update(AppointmentDTO payload, Integer id) {
        Appointment appointment = this.getById(id);
        appointment.setAppointmentDate(payload.appointmentDate());
        appointment.setStatus(payload.status());
        return appointment;
    }

    public List<Appointment> bulkInsert(List<AppointmentDTO> paayload, Integer doctorId, Integer patientId) {
        int batchSize = 30;
        int total = paayload.size();
        Patient patient = this.patientService.getById(patientId);
        Doctor doctor = this.doctorService.getById(doctorId);
        List<Appointment> response = new ArrayList<>();
        List<Appointment> appointments = paayload
                .stream()
                .map(e -> new Appointment(e, doctor, patient))
                .toList();

        for (int i = 0; i < total; i += batchSize) {
            int end = Math.min(i + batchSize, total);
            response.addAll(appointmentRepository.saveAll(appointments.subList(i, end)));
        }
        return response;
    }

    @Override
    public void delete(Integer id) {
        this.appointmentRepository.delete(this.getById(id));
    }
}

