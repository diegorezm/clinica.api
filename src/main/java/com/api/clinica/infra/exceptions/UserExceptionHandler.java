package com.api.clinica.infra.exceptions;

import com.api.clinica.domain.Error.ErrorDTO;
import com.api.clinica.domain.user.exceptions.UserEmailExistsException;
import com.api.clinica.domain.user.exceptions.UserNotFoundExeception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({UserEmailExistsException.class})
    public ResponseEntity<ErrorDTO> handler(UserNotFoundExeception e){
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), 401);
        return ResponseEntity.status(errorDTO.status()).body(errorDTO);
    }
}
