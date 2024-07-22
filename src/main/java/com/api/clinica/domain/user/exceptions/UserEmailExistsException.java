package com.api.clinica.domain.user.exceptions;

public class UserEmailExistsException extends RuntimeException{
    public UserEmailExistsException(){
        super("Este email já existe.");
    }
}
