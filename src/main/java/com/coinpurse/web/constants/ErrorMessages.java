package com.coinpurse.web.constants;

public final class ErrorMessages {
    private ErrorMessages() {}
    public static final String EXCEPTION_MESSAGE = "An exception has occurred.";
    public static final String EVENT_NOT_FOUND = "Event not found";
    public static final String PURSE_NOT_FOUND = "Purse not found";
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String ROLE_IN_USE = "Role in use";
    public static final String CURRENCY_NOT_FOUND = "Currency not found";

    // Validation
    public static final String ID_NULL = "Id field cannot be null";
    public static final String ROLE_NAME_EMPTY = "Role name cannot be empty";
    public static final String PURSE_NAME_EMPTY = "Purse name cannot be empty";
    public static final String CURRENCY_NAME_EMPTY = "Currency name cannot be empty";
    public static final String NULL = " may not be null";
    public static final String DATE_FIELD = "Date";
    public static final String DATE_NULL = DATE_FIELD + NULL;
    public static final String VALUE_FIELD = "Delta value";
    public static final String VALUE_NULL = VALUE_FIELD + NULL;
    public static final String CURRENCY_FIELD = "Currency";
    public static final String CURRENCY_NULL = CURRENCY_FIELD + NULL;
}
