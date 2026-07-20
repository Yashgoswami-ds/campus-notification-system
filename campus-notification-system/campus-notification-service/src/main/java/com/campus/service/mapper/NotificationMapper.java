package com.campus.service.mapper;

import com.campus.service.dto.NotificationRequestDto;
import com.campus.service.dto.NotificationResponseDto;
import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.entity.User;

public class NotificationMapper {

public static Notification mapToEntity(NotificationRequestDto dto, User user){
    return Notification.builder()
            .user(user)
            .title(dto.getTitle())
            .message(dto.getMessage())
            .priority(dto.getPriority())
            .status(NotificationStatus.PENDING)
            .isRead(false)
            .build();
}

public  static  NotificationResponseDto mapToResponse(Notification notification){
    return NotificationResponseDto.builder()
            .id(notification.getId())
            .userId(notification.getUser().getId())
            .title(notification.getTitle())
            .message(notification.getMessage())
            .priority(notification.getPriority())
            .status(notification.getStatus())
            .isRead(notification.isRead())
            .createdAt(notification.getCreatedAt())
            .updatedAt(notification.getUpdatedAt())
            .build();
    }
}
