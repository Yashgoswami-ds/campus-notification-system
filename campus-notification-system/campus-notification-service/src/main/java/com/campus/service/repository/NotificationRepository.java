package com.campus.service.repository;

import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationPriority;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
        List<Notification>findByPriority(NotificationPriority notificationPriority);
        List<Notification> findByStatus(NotificationStatus notificationStatus);
        List<Notification>findByUser(User user);
        List<Notification>findByIsRead(boolean isRead);
        List<Notification>findByUserAndPriority(User user, NotificationPriority notificationPriority);

}
