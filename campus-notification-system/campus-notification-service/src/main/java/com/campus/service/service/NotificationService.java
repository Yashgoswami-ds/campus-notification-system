package com.campus.service.service;

import com.campus.service.dto.NotificationRequestDto;
import com.campus.service.dto.NotificationResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface NotificationService {

    NotificationResponseDto createNotification(@Valid NotificationRequestDto requestDto);

    NotificationResponseDto getNotificationById(Long id);
    List<NotificationResponseDto> getAllNotifications();
    List<NotificationResponseDto> getNotificationsByUser(Long userId);

    NotificationRequestDto updateNotification(Long id, NotificationResponseDto requestDto);


    NotificationResponseDto   updateNotification(Long id, NotificationRequestDto requestDto);

    void deleteNotification(Long id );





}
