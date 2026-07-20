package com.campus.logging.mapper;

import com.campus.logging.dto.ErrorResponse;

import java.time.LocalDateTime;

public class ErrorResponseMapper {

    private ErrorResponseMapper(){}

    public   static  ErrorResponse  toResponse(
              int status,
              String error,
              String message,
              String path


    ){
         return ErrorResponse.builder()
                 .timestamp(LocalDateTime.now())
                 .status(status)
                 .error(error)
                 .message(message)
                 .path(path)
                 .build();
    }
}
