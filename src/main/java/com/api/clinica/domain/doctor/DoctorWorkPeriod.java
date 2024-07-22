package com.api.clinica.domain.doctor;

import com.api.clinica.domain.doctor.cpks.DoctorWorkPeriodId;
import com.api.clinica.domain.doctor.dtos.DoctorWorkPeriodDTO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors_work_period")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWorkPeriod {
    @EmbeddedId
    private DoctorWorkPeriodId id;

    public DoctorWorkPeriod(DoctorWorkPeriodDTO payload) {
        this.id = new DoctorWorkPeriodId(payload.doctorId(), payload.period());
    }
}
