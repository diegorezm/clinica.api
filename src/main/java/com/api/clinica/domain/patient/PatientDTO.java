package com.api.clinica.domain.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PatientDTO(
        @NotNull @NotBlank @Size(min = 4, max = 255)
        String name,
        @NotBlank @NotNull @Size(min = 14)
        String phone,
        @Size(min = 9)
        String rg,
        String insurance,
        String insuranceNumber
) {
}
