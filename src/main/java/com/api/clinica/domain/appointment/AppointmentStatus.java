package com.api.clinica.domain.appointment;

public enum AppointmentStatus {
    FALTA("f"),
    FALTA_JUSTIFICADA("fj"),
    FALTA_DO_MEDICO("fm"),
    OK("ok");

    private final String code;

    AppointmentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
