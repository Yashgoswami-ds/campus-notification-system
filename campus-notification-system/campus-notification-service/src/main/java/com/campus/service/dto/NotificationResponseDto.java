package com.campus.service.dto;

import com.campus.service.entity.NotificationPriority;
import com.campus.service.entity.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDto {

    private Long id;

    private Long userId;

    private String title;

    private String message;

    private NotificationPriority priority;

    private NotificationStatus status;

    private boolean isRead;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}