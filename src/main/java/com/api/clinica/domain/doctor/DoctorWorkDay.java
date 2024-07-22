package com.api.clinica.domain.doctor;

import com.api.clinica.domain.doctor.cpks.DoctorWorkDayId;
import com.api.clinica.domain.doctor.dtos.DoctorWorkDayDTO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors_work_day")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWorkDay {
    @EmbeddedId
    private DoctorWorkDayId id;

    public DoctorWorkDay(DoctorWorkDayDTO payload) {
        this.id = new DoctorWorkDayId(payload.doctorId(), payload.day());
    }
}
