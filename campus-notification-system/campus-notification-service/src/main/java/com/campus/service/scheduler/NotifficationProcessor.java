package com.campus.service.scheduler;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor

public class NotifficationProcessor {

    private  final  NotificationQueueService notificationQueueService;


    public  void  ProcessNextNotification(){
        NotificationTask task =notificationQueueService.getPeekNotification();
        if(task==null){
            return ;
        }
    }



}
