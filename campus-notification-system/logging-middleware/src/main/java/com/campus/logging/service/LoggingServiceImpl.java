package com.campus.logging.service;

import com.campus.logging.dto.LogRequest;
import com.campus.logging.dto.LogResponse;
import com.campus.logging.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class LoggingServiceImpl implements LoggingService {

    @Override
    public LogResponse logRequest(LogRequest request) {

        log.info(
                "RequestId={} | App={} | Method={} | Path={} | Body={}",
                request.getRequestId(),
                request.getApplicationName(),
                request.getHttpMethod(),
                request.getApiPath(),
                JsonUtil.toJson(request.getRequestBody())
        );

        return createResponse(request.getRequestId(), "SUCCESS", "Request logged successfully");
    }

    @Override
    public LogResponse logResponse(LogRequest request) {

        log.info(
                "Response | RequestId={} | Path={}",
                request.getRequestId(),
                request.getApiPath()
        );

        return createResponse(request.getRequestId(), "SUCCESS", "Response logged successfully");
    }

    @Override
    public LogResponse logException(LogRequest request, Exception exception) {

        log.error(
                "Exception | RequestId={} | Path={} | Message={}",
                request.getRequestId(),
                request.getApiPath(),
                exception.getMessage(),
                exception
        );

        return createResponse(request.getRequestId(), "FAILED", "Exception logged");
    }

    private LogResponse createResponse(String requestId, String status, String message) {

        return LogResponse.builder()
                .requestId(requestId)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}