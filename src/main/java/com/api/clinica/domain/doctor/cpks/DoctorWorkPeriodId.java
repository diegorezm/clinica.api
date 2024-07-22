package com.api.clinica.domain.doctor.cpks;

import com.api.clinica.domain.doctor.DoctorPeriod;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorWorkPeriodId implements Serializable {
    private Integer doctorId;
    @Enumerated(EnumType.STRING)
    private DoctorPeriod period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorWorkPeriodId that = (DoctorWorkPeriodId) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, period);
    }
}
