package com.coinpurse.web.domain.exceptions;

public record ValidationError(String field, String message) {
}
