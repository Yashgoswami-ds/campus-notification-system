package com.campus.logging.service;

import com.campus.logging.dto.LogRequest;
import com.campus.logging.dto.LogResponse;

public interface LoggingService {
    LogResponse logRequest(LogRequest request);

    LogResponse logResponse(LogRequest request);
    LogResponse logException (LogRequest request , Exception exception);
}
