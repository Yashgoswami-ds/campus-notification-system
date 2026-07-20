package com.campus.logging.validation;

import com.campus.logging.dto.LogRequest;
import com.campus.logging.exception.LoggingException;

public final class LogValidator {

    private LogValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(LogRequest request) {

        if (request == null) {
            throw new LoggingException("Log request cannot be null.");
        }

        if (request.getApplicationName() == null || request.getApplicationName().isBlank()) {
            throw new LoggingException("Application name cannot be blank.");
        }

        if (request.getHttpMethod() == null) {
            throw new LoggingException("HTTP method cannot be null.");
        }

        if (request.getApiPath() == null || request.getApiPath().isBlank()) {
            throw new LoggingException("API path cannot be blank.");
        }
    }
}