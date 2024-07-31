package com.api.clinica.services.v1;

import com.api.clinica.domain.appointment.Appointment;
import com.api.clinica.domain.appointment.dtos.AppointmentDTO;
import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.patient.Patient;
import com.api.clinica.repositories.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private DoctorService doctorService;

    @Mock
    private PatientService patientService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void bulkInsert() {
        int doctorId = 1;
        int patientId = 2;
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        List<AppointmentDTO> payload = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            var app = Appointment.createMockAppointment(patient, doctor);
            payload.add(new AppointmentDTO(doctorId, patientId, app.getStatus(), app.getAppointmentDate())); // Adjust this if your constructor is different
        }
        when(patientService.getById(patientId)).thenReturn(patient);
        when(doctorService.getById(doctorId)).thenReturn(doctor);
        when(appointmentRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0)); // Return the same list passed to saveAll

        var response = appointmentService.bulkInsert(payload, doctorId, patientId);

        assertEquals(35, response.size());
    }

}
