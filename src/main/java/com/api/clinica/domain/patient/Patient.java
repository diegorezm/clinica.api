package com.api.clinica.domain.patient;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Patients")
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String phone;
    private String rg;
    private String insurance;
    private String insuranceNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "patient",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PatientReferral> referrals;

    public Patient(PatientDTO payload) {
        this.name = payload.name();
        this.phone = payload.phone();
        this.rg = payload.rg();
        this.insurance = payload.insurance();
        this.insuranceNumber = payload.insuranceNumber();
    }
}
