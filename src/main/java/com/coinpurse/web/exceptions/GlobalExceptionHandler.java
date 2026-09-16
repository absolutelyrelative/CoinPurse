package com.coinpurse.web.exceptions;

import com.coinpurse.web.constants.ErrorMessages;
import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.exceptions.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e) {
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Redundant when using ResponseEntity<>
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception e) {
        ExceptionDto exceptionResponse = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorMessages.EXCEPTION_MESSAGE, LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
