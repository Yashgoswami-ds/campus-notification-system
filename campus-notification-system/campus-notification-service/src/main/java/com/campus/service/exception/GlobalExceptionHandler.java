package com.campus.service.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleResourceNotFound(
            ResourceNotFoundException ex , HttpServletRequest request
    ){
           ErrorResponse  errorResponse =ErrorResponse.builder()
                   .timestamp(LocalDateTime.now())
                   .status(HttpStatus.NOT_FOUND.value())
                   .message(ex.getMessage()).path(request.getRequestURI())
                   .build();

           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .path(request.getRequestURI())
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
               .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Some thing Went Wrong ").path(request.getRequestURI())
                .build();
        return  ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(errorResponse);
    }

}
