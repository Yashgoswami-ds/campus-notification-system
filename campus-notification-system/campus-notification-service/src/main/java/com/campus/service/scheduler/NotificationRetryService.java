package com.campus.service.scheduler;

import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationRetryService {

    private final NotificationRepository notificationRepository;
    private final NotificationQueueService notificationQueueService;


    @Scheduled(fixedDelay = 30000)
    public void retryFailedNotifications() {

        List<Notification> failedNotifications =
                notificationRepository.findByStatus(NotificationStatus.FAILED);


        for(Notification notification : failedNotifications) {

            if(notification.getRetryCount() < 3) {


                NotificationTask task =
                        new NotificationTask(
                                notification.getId(),
                                notification.getPriority(),
                                notification.getCreatedAt()
                        );


                notificationQueueService.addTask(task);


                notification.setStatus(NotificationStatus.PENDING);
                notificationRepository.save(notification);


                log.info(
                        "Retrying notification id: {} attempt: {}",
                        notification.getId(),
                        notification.getRetryCount()
                );

            }
        }
    }
}