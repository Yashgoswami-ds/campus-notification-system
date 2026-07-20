package com.campus.logging.exception;

import com.campus.logging.dto.ErrorResponse;
import com.campus.logging.mapper.ErrorResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalLoggingExceptionHandler {
@ExceptionHandler(LoggingException.class)
    public ResponseEntity<ErrorResponse>
    handdleloggingexception(LoggingException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseMapper.toResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), "LOGGING_ERROR",exception.getMessage(),null
                ));
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse>handleGenericException(Exception exception){
    return  ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponseMapper.toResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "INTERNAL_SERVER_ERROR" , exception.getMessage(),
                            null
                    )
            );
    }

}
