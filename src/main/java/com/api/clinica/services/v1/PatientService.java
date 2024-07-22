package com.api.clinica.services.v1;

import com.api.clinica.domain.patient.Patient;
import com.api.clinica.domain.patient.PatientDTO;
import com.api.clinica.domain.patient.PatientReferral;
import com.api.clinica.domain.patient.PatientReferralDTO;
import com.api.clinica.domain.patient.exceptions.PatientNotFoundException;
import com.api.clinica.repositories.PatientReferralRepository;
import com.api.clinica.repositories.PatientRepository;
import com.api.clinica.services.ServiceInterface;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService implements ServiceInterface<Patient, PatientDTO> {

    private final PatientRepository patientRepository;
    private final PatientReferralRepository patientReferralRepository;

    @Override
    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient getById(Integer id) {
        return this.patientRepository.findById(id).orElseThrow(
                PatientNotFoundException::new
            );
    }

    @Override
    public Patient create(PatientDTO payload) {
        return this.patientRepository.save(new Patient(payload));
    }

    @Override
    public Patient update(PatientDTO payload, Integer id) {
        Patient patient = this.getById(id);
        patient.setName(payload.name());
        patient.setRg(payload.rg());
        patient.setPhone(payload.phone());
        patient.setInsurance(payload.insurance());
        patient.setInsuranceNumber(payload.insuranceNumber());
        patient.setUpdatedAt(LocalDateTime.now());
        this.patientRepository.save(patient);
        return patient;
    }

    @Override
    public void delete(Integer id) {
        this.patientRepository.delete(this.getById(id));
    }

    public List<PatientReferral> getAllReferrals(Integer patientId) {
        return this.patientReferralRepository.findByPatientId(patientId);
    }

    public PatientReferral addPatientReferral(PatientReferralDTO payload) {
        Patient patient = this.getById(payload.patientId());
        return this.patientReferralRepository.save(new PatientReferral(payload, patient));
    }

    public void removePatientReferral(Integer id) {
        this.patientReferralRepository.deleteById(id);
    }
}
