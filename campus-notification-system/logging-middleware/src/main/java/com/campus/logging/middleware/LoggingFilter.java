package com.campus.logging.middleware;

import com.campus.logging.dto.LogRequest;
import com.campus.logging.service.LoggingService;
import com.campus.logging.util.RequestIdGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {


    private final LoggingService loggingService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        String requestId = RequestIdGenerator.generateRequestId();


        LogRequest logRequest = LogRequest.builder()
                .requestId(requestId)
                .applicationName("campus-notification-service")
                .httpMethod(HttpMethod.valueOf(request.getMethod()))
                .apiPath(request.getRequestURI())
                .build();


        // Before controller
        loggingService.logRequest(logRequest);


        try {

            filterChain.doFilter(request, response);


            // After controller
            loggingService.logResponse(logRequest);


        } catch (Exception e) {

            loggingService.logException(logRequest, e);

            throw e;
        }

    }
}