package com.campus.service.scheduler;

import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.repository.NotificationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationRecoveryService {

    private  final NotificationRepository notificationRepository;
    private  final  NotificationQueueService notificationQueueService;

    @PostConstruct
public  void  loadPendingNotifications() {
        List<Notification> pendingNotifications = notificationRepository.findByStatus(NotificationStatus.PENDING);
        log.info("Recovering {} pending notifications",
                pendingNotifications.size());


        for (Notification notification : pendingNotifications) {
            NotificationTask notificationTask = new NotificationTask(notification.getId(), notification.getPriority(), notification.getCreatedAt());

            notificationQueueService.addTask(notificationTask);

            log.info(
                    "Recovered notification id: {} priority: {}",
                    notification.getId(),
                    notification.getPriority()
            );
        }

    }

}
