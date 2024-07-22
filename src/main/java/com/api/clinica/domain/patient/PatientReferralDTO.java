package com.api.clinica.domain.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PatientReferralDTO(
    @NotNull String crm,
    @NotNull String cid,
    @NotNull String job,
    @NotNull @Positive Integer patientId
) {}
