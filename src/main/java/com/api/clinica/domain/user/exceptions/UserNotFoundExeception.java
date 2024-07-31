package com.api.clinica.domain.user.exceptions;

import com.api.clinica.domain.Error.exceptions.NotFoundException;

public class UserNotFoundExeception extends NotFoundException {
    public UserNotFoundExeception(){
        super("Usuário não encontrado.");
    }
}
