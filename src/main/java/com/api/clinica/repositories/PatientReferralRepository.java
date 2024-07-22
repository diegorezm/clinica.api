package com.api.clinica.repositories;

import com.api.clinica.domain.patient.PatientReferral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientReferralRepository extends JpaRepository<PatientReferral, Integer> {
    List<PatientReferral> findByPatientId(Integer id);
}
