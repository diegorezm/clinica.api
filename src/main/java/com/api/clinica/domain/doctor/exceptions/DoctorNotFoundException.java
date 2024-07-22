package com.api.clinica.domain.doctor.exceptions;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(){
        super("Este doutor não foi encontrado.");
    }
}
