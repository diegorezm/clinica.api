package com.api.clinica.domain.user;

import jakarta.validation.constraints.*;

public record UserDTO(
        @NotNull @Size(min =  6, max = 255)
        String name,
        @NotNull @Size(min = 12, max = 255)
        String email,
        @NotNull @Size(min = 6, max = 255)
        String password
) {
}
