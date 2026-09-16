package com.coinpurse.web.dto.exceptions;

import java.time.LocalDateTime;

// Using it instead of ProblemDetail just for exercise
public record ExceptionDto(int status, String message, LocalDateTime timestamp) {
}
