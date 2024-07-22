package com.api.clinica.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "PatientReferrals")
@Table(name = "patient_referrals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientReferral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String crm;
    private String cid;
    private String job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public PatientReferral(PatientReferralDTO payload, Patient patient) {
        this.crm = payload.crm();
        this.cid = payload.cid();
        this.job = payload.job();
        this.patient = patient;
    }
}
