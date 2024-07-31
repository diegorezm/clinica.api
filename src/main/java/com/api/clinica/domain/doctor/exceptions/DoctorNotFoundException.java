package com.api.clinica.domain.doctor.exceptions;


import com.api.clinica.domain.Error.exceptions.NotFoundException;

public class DoctorNotFoundException extends NotFoundException {
    public DoctorNotFoundException(){
        super("Este doutor não foi encontrado.");
    }
}
