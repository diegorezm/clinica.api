package com.api.clinica.domain.patient.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(){
        super("Paciente não encontrado.");
    }
}
