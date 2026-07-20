package com.campus.service.scheduler;

import com.campus.service.entity.NotificationPriority;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class NotificationTask {

    private  Long NotificationId;
    private NotificationPriority priority;
    private LocalDateTime createdAt;
}
