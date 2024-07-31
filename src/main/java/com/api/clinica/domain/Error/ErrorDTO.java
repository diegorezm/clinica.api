package com.api.clinica.domain.Error;

public record ErrorDTO(
        String message,
        int status
) {
}
