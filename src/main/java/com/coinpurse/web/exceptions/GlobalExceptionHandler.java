package com.coinpurse.web.exceptions;

import com.coinpurse.web.constants.ErrorMessages;
import com.coinpurse.web.domain.exceptions.ResourceInUseException;
import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.domain.exceptions.ValidationError;
import com.coinpurse.web.domain.exceptions.ValidationException;
import com.coinpurse.web.dto.exceptions.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> validationErrors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Validation failed"
        );
        problem.setProperty("errors", validationErrors);

        return problem;
    }

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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceInUseException.class)
    public ProblemDetail handleResourceInUseException(ResourceInUseException e){
        return ProblemDetail.forStatus(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidationException(ValidationException validationException){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_CONTENT, validationException.getMessage()
        );

        problem.setProperty("errors", validationException.getValidationErrors());
        return problem;
    }


}
