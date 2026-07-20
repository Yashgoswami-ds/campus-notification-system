package com.campus.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationScheduler {
 private  final NotificationQueueService notificationQueueService;
 private  final  NotificationProcessor notificationProcessor;


 @Scheduled(fixedDelay = 5000)
 public  void processQueue(){

      if(notificationQueueService.isEmpty()){
          log.info("Schedular is Empty");
          return ;
      }
      log.info("Schedular :found Notification in Queue");
      notificationProcessor.processNextNotification();
 }

}
