package com.campus.logging.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
@Builder
public class LogRequest {

    private  String requestId;
    private  String applicationName;
    private HttpMethod httpMethod;
    private  String apiPath;
    private  Object requestBody;
    private  String userId;




}


