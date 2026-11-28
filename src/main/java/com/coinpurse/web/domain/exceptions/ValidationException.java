package com.coinpurse.web.domain.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ValidationError> validationErrors;

    public ValidationException(List<ValidationError> validationErrors) {
        super("Validation failed: " + validationErrors.size() + " error(s).");
        this.validationErrors = List.copyOf(validationErrors);
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
