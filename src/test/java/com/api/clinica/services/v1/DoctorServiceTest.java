package com.api.clinica.services.v1;

import com.api.clinica.domain.doctor.*;
import com.api.clinica.domain.doctor.cpks.DoctorWorkDayId;
import com.api.clinica.domain.doctor.cpks.DoctorWorkPeriodId;
import com.api.clinica.domain.doctor.dtos.DoctorDTO;
import com.api.clinica.domain.doctor.dtos.DoctorWorkDayDTO;
import com.api.clinica.domain.doctor.dtos.DoctorWorkPeriodDTO;
import com.api.clinica.domain.doctor.exceptions.DoctorNotFoundException;
import com.api.clinica.domain.user.User;
import com.api.clinica.domain.user.exceptions.UserNotFoundExeception;
import com.api.clinica.repositories.DoctorRepository;
import com.api.clinica.repositories.DoctorWorkDayRepository;
import com.api.clinica.repositories.DoctorWorkPeriodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorWorkDayRepository doctorWorkDayRepository;

    @Mock
    private DoctorWorkPeriodRepository doctorWorkPeriodRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_shouldThrowException_whenDoctorNotFound() {
        // Arrange
        when(doctorRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DoctorNotFoundException.class, () -> doctorService.getById(1));
    }

    @Test
    void addWorkDay_shouldSaveAndReturnDoctorWorkDay() {
        // Arrange
        DoctorWorkDayDTO dto = new DoctorWorkDayDTO(1, WeekDay.MONDAY);
        DoctorWorkDay workDay = new DoctorWorkDay(dto);
        when(doctorRepository.findById(dto.doctorId())).thenReturn(Optional.of(new Doctor()));
        when(doctorWorkDayRepository.save(any(DoctorWorkDay.class))).thenReturn(workDay);

        // Act
        DoctorWorkDay result = doctorService.addWorkDay(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.doctorId(), result.getId().getDoctorId());
        assertEquals(dto.day(), result.getId().getDay());
        verify(doctorWorkDayRepository, times(1)).save(any(DoctorWorkDay.class));
    }

    @Test
    void removeWorkDay_shouldDeleteDoctorWorkDayById() {
        // Arrange
        DoctorWorkDayDTO dto = new DoctorWorkDayDTO(1, WeekDay.FRIDAY);
        DoctorWorkDayId id = new DoctorWorkDayId(dto.doctorId(), dto.day());

        // Act
        doctorService.removeWorkDay(dto);

        // Assert
        verify(doctorWorkDayRepository, times(1)).deleteById(id);
    }

    @Test
    void addWorkPeriod_shouldSaveAndReturnDoctorWorkPeriod() {
        // Arrange
        DoctorWorkPeriodDTO dto = new DoctorWorkPeriodDTO(1, DoctorPeriod.M);
        DoctorWorkPeriod period = new DoctorWorkPeriod(dto);
        when(doctorRepository.findById(dto.doctorId())).thenReturn(Optional.of(new Doctor()));
        when(doctorWorkPeriodRepository.save(any(DoctorWorkPeriod.class))).thenReturn(period);

        // Act
        DoctorWorkPeriod result = doctorService.addWorkPeriod(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.doctorId(), result.getId().getDoctorId());
        assertEquals(dto.period(), result.getId().getPeriod());
        verify(doctorWorkPeriodRepository, times(1)).save(any(DoctorWorkPeriod.class));
    }

    @Test
    void removeWorkPeriod_shouldDeleteDoctorWorkPeriodById() {
        // Arrange
        DoctorWorkPeriodDTO dto = new DoctorWorkPeriodDTO(1, DoctorPeriod.M);
        DoctorWorkPeriodId id = new DoctorWorkPeriodId(dto.doctorId(), dto.period());

        // Act
        doctorService.removeWorkPeriod(dto);

        // Assert
        verify(doctorWorkPeriodRepository, times(1)).deleteById(id);
    }

    @Test
    void create_shouldThrowException_whenUserNotFound() {
        // Arrange
        DoctorDTO dto = new DoctorDTO("123", "Cardiologist", UUID.randomUUID().toString());
        when(userService.getById(any(UUID.class))).thenThrow(new UserNotFoundExeception());

        // Act & Assert
        assertThrows(UserNotFoundExeception.class, () -> doctorService.create(dto));
    }

    @Test
    void create_shouldSaveAndReturnDoctor() {
        // Arrange
        DoctorDTO dto = new DoctorDTO("123", "Cardiologist", UUID.randomUUID().toString());
        User user = new User();
        Doctor doctor = new Doctor(dto, user);
        when(userService.getById(any(UUID.class))).thenReturn(user);
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor result = doctorService.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.crm(), result.getCrm());
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }
}
