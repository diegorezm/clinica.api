package com.api.clinica.domain.user.exceptions;

public class UserNotFoundExeception extends RuntimeException{
    public UserNotFoundExeception(){
        super("Usuário não encontrado.");
    }
}
