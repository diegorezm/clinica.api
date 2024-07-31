package com.api.clinica.infra.exceptions;

import com.api.clinica.domain.Error.ErrorDTO;
import com.api.clinica.domain.Error.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDTO> handler(NotFoundException e){
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(errorDTO.status()).body(errorDTO);
    }
}
