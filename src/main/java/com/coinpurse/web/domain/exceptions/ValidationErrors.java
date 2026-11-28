package com.coinpurse.web.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {

    private final List<ValidationError> validationErrors = new ArrayList<>();

    public ValidationErrors add(String field, String message){
        validationErrors.add(new ValidationError(field, message));
        return this;
    }

    public ValidationErrors check(boolean condition, String field, String message){
        if(!condition){
            add(field, message);
        }
        return this;
    }

    public void throwIfAny(){
        if(!validationErrors.isEmpty()){
            throw new ValidationException(validationErrors);
        }
    }
}
