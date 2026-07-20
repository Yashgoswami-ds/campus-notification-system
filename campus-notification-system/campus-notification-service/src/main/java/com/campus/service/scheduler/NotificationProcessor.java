package com.campus.service.scheduler;

import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessor {

    private final NotificationQueueService notificationQueueService;
    private final NotificationRepository notificationRepository;

    public void processNextNotification() {

        // STEP 1: Get highest priority task
        NotificationTask task =
                notificationQueueService.getNextNotification();

        // Queue empty
        if (task == null) {
            log.info("No notifications in queue.");
            return;
        }

        log.info("Processing notification id: {}", task.getNotificationId());

        // STEP 2: Load notification from DB
        Notification notification = notificationRepository
                .findById(task.getNotificationId())
                .orElse(null);

        if (notification == null) {
            log.warn("Notification not found in DB: {}", task.getNotificationId());
            return;
        }

        try {

            // STEP 3: Mark as PROCESSING
            notification.setStatus(NotificationStatus.PROCESSING);
            notificationRepository.save(notification);

            // STEP 4: Simulate sending
            log.info("Sending notification: {}", notification.getTitle());

            // STEP 5: Mark as SENT
            notification.setStatus(NotificationStatus.SENT);
            notificationRepository.save(notification);

            log.info("Notification SENT successfully: {}", notification.getId());

        } catch (Exception e) {

            // STEP 6: Mark as FAILED
            notification.setStatus(NotificationStatus.FAILED);
            notificationRepository.save(notification);

            log.error("Notification FAILED: {}", notification.getId(), e);
        }
    }
}