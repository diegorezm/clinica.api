package com.api.clinica.domain.patient.exceptions;

import com.api.clinica.domain.Error.exceptions.NotFoundException;

public class PatientNotFoundException extends NotFoundException {
    public PatientNotFoundException(){
        super("Paciente não encontrado.");
    }
}
