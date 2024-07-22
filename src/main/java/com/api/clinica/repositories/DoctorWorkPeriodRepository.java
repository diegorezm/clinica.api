package com.api.clinica.repositories;

import com.api.clinica.domain.doctor.DoctorWorkPeriod;
import com.api.clinica.domain.doctor.cpks.DoctorWorkPeriodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorWorkPeriodRepository extends JpaRepository<DoctorWorkPeriod, DoctorWorkPeriodId> {
}
