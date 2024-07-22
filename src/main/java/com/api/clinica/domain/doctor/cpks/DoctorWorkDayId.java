package com.api.clinica.domain.doctor.cpks;

import com.api.clinica.domain.doctor.WeekDay;
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
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWorkDayId implements Serializable {
    private Integer doctorId;

    @Enumerated(EnumType.STRING)
    private WeekDay day;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorWorkDayId that = (DoctorWorkDayId) o;
        return Objects.equals(doctorId, that.doctorId) && day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, day);
    }
}
