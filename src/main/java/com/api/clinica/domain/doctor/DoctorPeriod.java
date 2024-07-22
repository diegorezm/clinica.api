package com.api.clinica.domain.doctor;

import lombok.Getter;

@Getter
public enum DoctorPeriod {
    M("m"),  // Morning
    T("t"),  // Afternoon
    N("n");  // Night

    private final String code;

    DoctorPeriod(String code) {
        this.code = code;
    }

    public static DoctorPeriod fromCode(String code) {
        for (DoctorPeriod period : DoctorPeriod.values()) {
            if (period.getCode().equals(code)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
