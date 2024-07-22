package com.api.clinica.services.v1;

import com.api.clinica.domain.doctor.Doctor;
import com.api.clinica.domain.doctor.DoctorWorkDay;
import com.api.clinica.domain.doctor.DoctorWorkPeriod;
import com.api.clinica.domain.doctor.cpks.DoctorWorkDayId;
import com.api.clinica.domain.doctor.cpks.DoctorWorkPeriodId;
import com.api.clinica.domain.doctor.dtos.DoctorDTO;
import com.api.clinica.domain.doctor.dtos.DoctorWorkDayDTO;
import com.api.clinica.domain.doctor.dtos.DoctorWorkPeriodDTO;
import com.api.clinica.domain.doctor.exceptions.DoctorNotFoundException;
import com.api.clinica.domain.user.User;
import com.api.clinica.repositories.DoctorRepository;
import com.api.clinica.repositories.DoctorWorkDayRepository;
import com.api.clinica.repositories.DoctorWorkPeriodRepository;
import com.api.clinica.services.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DoctorService implements ServiceInterface<Doctor, DoctorDTO> {
    private final DoctorRepository doctorRepository;
    private final DoctorWorkDayRepository doctorWorkDayRepository;
    private final DoctorWorkPeriodRepository doctorWorkPeriodRepository;
    private final UserService userService;

    @Override
    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor getById(Integer id) {
        return this.doctorRepository.findById(id).orElseThrow(DoctorNotFoundException::new);
    }

    @Override
    public Doctor create(DoctorDTO payload) {
        User user = this.userService.getById(UUID.fromString(payload.userId()));
        return this.doctorRepository.save(new Doctor(payload, user));
    }

    @Override
    public Doctor update(DoctorDTO payload, Integer id) {
        Doctor doctor = this.getById(id);
        doctor.setJob(payload.job());
        doctor.setCrm(payload.crm());
        doctor.setUpdatedAt(LocalDateTime.now());
        this.doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void delete(Integer id) {
        this.doctorRepository.delete(this.getById(id));
    }

    public DoctorWorkDay addWorkDay(DoctorWorkDayDTO payload) {
        // should throw if doctor does not exist
        this.getById(payload.doctorId());
        DoctorWorkDay workDay = new DoctorWorkDay(payload);
        return this.doctorWorkDayRepository.save(workDay);
    }

    public void removeWorkDay(DoctorWorkDayDTO payload) {
        DoctorWorkDayId id = new DoctorWorkDayId(payload.doctorId(), payload.day());
        this.doctorWorkDayRepository.deleteById(id);
    }

    public DoctorWorkPeriod addWorkPeriod(DoctorWorkPeriodDTO payload) {
        // should throw if doctor does not exist
        this.getById(payload.doctorId());
        DoctorWorkPeriod period = new DoctorWorkPeriod(payload);
        return this.doctorWorkPeriodRepository.save(period);
    }

    public void removeWorkPeriod(DoctorWorkPeriodDTO payload) {
        DoctorWorkPeriodId id = new DoctorWorkPeriodId(payload.doctorId(), payload.period());
        this.doctorWorkPeriodRepository.deleteById(id);
    }
}
