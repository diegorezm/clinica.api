package com.api.clinica.repositories;

import com.api.clinica.domain.doctor.DoctorWorkDay;
import com.api.clinica.domain.doctor.cpks.DoctorWorkDayId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorWorkDayRepository extends JpaRepository<DoctorWorkDay, DoctorWorkDayId> {
}
