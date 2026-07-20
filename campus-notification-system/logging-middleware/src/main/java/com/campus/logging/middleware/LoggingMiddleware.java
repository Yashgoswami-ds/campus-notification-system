package com.campus.logging.middleware;

import com.campus.logging.dto.LogRequest;
import com.campus.logging.dto.LogResponse;
import com.campus.logging.service.LoggingService;
import com.campus.logging.util.RequestIdGenerator;
import com.campus.logging.validation.LogValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoggingMiddleware {

    private  final LoggingService loggingService;

  public LogResponse logRequest(LogRequest request){
        LogValidator.validate(request);
        request.setRequestId(RequestIdGenerator.generateRequestId());
        return loggingService.logRequest(request);
    }

   public LogResponse logResponse(LogRequest request){

        return  loggingService.logResponse(request);


    }
    public LogResponse logException (LogRequest request , Exception exception){
                    return  loggingService.logException(request,exception);
    }
}
