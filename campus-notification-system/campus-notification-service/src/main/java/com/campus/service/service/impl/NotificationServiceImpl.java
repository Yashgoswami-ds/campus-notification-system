package com.campus.service.service.impl;

import com.campus.service.dto.NotificationRequestDto;
import com.campus.service.dto.NotificationResponseDto;
import com.campus.service.entity.Notification;
import com.campus.service.entity.NotificationPriority;
import com.campus.service.entity.NotificationStatus;
import com.campus.service.entity.User;
import com.campus.service.exception.ResourceNotFoundException;
import com.campus.service.mapper.NotificationMapper;
import com.campus.service.repository.NotificationRepository;
import com.campus.service.repository.UserRepository;
import com.campus.service.scheduler.NotificationQueueService;
import com.campus.service.scheduler.NotificationTask;
import com.campus.service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
private  final NotificationRepository notificationRepository;
private  final UserRepository userRepository;
private  final NotificationQueueService notificationQueueService;


@Override
public NotificationResponseDto createNotification(@Valid NotificationRequestDto requestDto){
    User user =userRepository.findById(requestDto.getUserId())
            .orElseThrow(()->
                    new ResourceNotFoundException("User not present with this id: "+ requestDto.getUserId()));
    Notification notification = NotificationMapper.mapToEntity(requestDto,user);
    Notification saveNotification =notificationRepository.save(notification);

    NotificationTask notificationTask= new  NotificationTask (saveNotification.getId(), saveNotification.getPriority(),saveNotification.getCreatedAt());

    notificationQueueService.addTask(notificationTask);
    return  NotificationMapper.mapToResponse(saveNotification);


}

@Override
public NotificationResponseDto getNotificationById(Long id){

    Notification notification= notificationRepository.findById(id)
            .orElseThrow(()->new RuntimeException("Notification Not Found"));

    return NotificationMapper.mapToResponse(notification);
}

@Override
public List<NotificationResponseDto> getAllNotifications(){
     return  notificationRepository.findAll()
             .stream()
             .map(NotificationMapper::mapToResponse).collect(Collectors.toList());

}

@Override
public  List<NotificationResponseDto>  getNotificationsByUser(Long userId){

    User user =userRepository.findById(userId)
            .orElseThrow(()->new RuntimeException("User not found."));


    return  notificationRepository.findByUser(user)
            .stream()
            .map(NotificationMapper::mapToResponse)
            .collect(Collectors.toList());
}

    @Override
    public NotificationRequestDto updateNotification(Long id, NotificationResponseDto requestDto) {
        return null;
    }

    @Override
public NotificationResponseDto   updateNotification(Long id, NotificationRequestDto requestDto)
{
    Notification notification=notificationRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Notification Not Found"));
    notification.setTitle(requestDto.getTitle());
    notification.setMessage(requestDto.getMessage());
    notification.setPriority(requestDto.getPriority());

    Notification updateNotification=notificationRepository.save(notification);



    return NotificationMapper.mapToResponse(updateNotification);
}



public   void deleteNotification(Long id){
    Notification
            notification = notificationRepository.findById(id)
            .orElseThrow(()->new RuntimeException("Notification Not Found "));
    notificationRepository.delete(notification);
}

    @Override
    public List<NotificationResponseDto> getNotificationsByPriority(NotificationPriority priority){

        return notificationRepository.findByPriority(priority)
                .stream()
                .map(NotificationMapper::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<NotificationResponseDto> getNotificationsByStatus(NotificationStatus status){

        return notificationRepository.findByStatus(status)
                .stream()
                .map(NotificationMapper::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public NotificationResponseDto markAsRead(Long id){

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification Not Found"));

        notification.setRead(true);

        Notification updatedNotification = notificationRepository.save(notification);

        return NotificationMapper.mapToResponse(updatedNotification);
    }

}
