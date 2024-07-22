package com.api.clinica.domain.doctor;

import com.api.clinica.domain.doctor.dtos.DoctorDTO;
import com.api.clinica.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Doctors")
@Table(name = "Doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String crm;
    private String job;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Doctor(DoctorDTO payload, User user){
        this.crm = payload.crm();
        this.job = payload.job();
        this.user = user;
    }
}
