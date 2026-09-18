package com.coinpurse.web.constants;

public final class ErrorMessages {
    private ErrorMessages() {}
    public static final String EXCEPTION_MESSAGE = "An exception has occurred.";
    public static final String EVENT_NOT_FOUND = "Event not found";
    public static final String PURSE_NOT_FOUND = "Purse not found";
    public static final String ROLE_NOT_FOUND = "Role not found";

    // Validation
    public static final String ID_NULL = "Id field cannot be null";
    public static final String ROLE_NAME_EMPTY = "Role name cannot be empty";
    public static final String PURSE_NAME_EMPTY = "Purse name cannot be empty";
    public static final String CURRENCY_NAME_EMPTY = "Currency name cannot be empty";
}
