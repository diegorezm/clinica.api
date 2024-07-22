package com.api.clinica.domain.doctor.dtos;

import com.api.clinica.domain.doctor.WeekDay;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DoctorWorkDayDTO(
        @NotNull @Positive
        Integer doctorId,
        @NotNull
        WeekDay day
) {
}
