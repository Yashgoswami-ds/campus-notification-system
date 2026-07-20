package com.campus.logging.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class LogResponse {
    private String requestId;
    private  String status;
    private String message;
    private LocalDateTime timestamp;


}
