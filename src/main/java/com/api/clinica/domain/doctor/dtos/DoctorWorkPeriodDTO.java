package com.api.clinica.domain.doctor.dtos;

import com.api.clinica.domain.doctor.DoctorPeriod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DoctorWorkPeriodDTO(
        @NotNull @Positive
        Integer doctorId,
        @NotNull
        DoctorPeriod period
) {
}
