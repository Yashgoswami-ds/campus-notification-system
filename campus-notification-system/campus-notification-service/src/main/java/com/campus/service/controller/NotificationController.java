package com.campus.service.controller;

import com.campus.service.dto.NotificationRequestDto;
import com.campus.service.dto.NotificationResponseDto;
import com.campus.service.scheduler.NotificationProcessor;
import com.campus.service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@RestController
public class NotificationController {
     private final NotificationService notificationService;
     private  final NotificationProcessor notificationProcessor;
     @PostMapping
     public ResponseEntity<NotificationResponseDto>createNotification(
       @Valid @RequestBody NotificationRequestDto requestDto ){

           NotificationResponseDto responseDto=notificationService.createNotification(requestDto);
          return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
     }

@GetMapping("/{id}")
     public  ResponseEntity<NotificationResponseDto>getNotificationById(@PathVariable Long id){
          return  ResponseEntity.ok(notificationService.getNotificationById(id));
     }

     @GetMapping
     public  ResponseEntity<List<NotificationResponseDto>>getNotificationsByUser(){
          return  ResponseEntity.ok(notificationService.getAllNotifications());
     }
     @GetMapping("/user/{userId}")
     public  ResponseEntity<List<NotificationResponseDto>>getNotificationsByUser( @PathVariable  Long userId){
           return  ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
     }
     @PutMapping("/{id}")
     public  ResponseEntity<NotificationResponseDto>updateNotification(@PathVariable Long id, @Valid @RequestBody   NotificationRequestDto responseDto)
     {
          return  ResponseEntity.ok(notificationService.updateNotification(id ,responseDto));
     }

     @DeleteMapping("/{id}")
     public  ResponseEntity<Void>deleteNotification(@PathVariable Long id){
          notificationService.deleteNotification(id);
          return  ResponseEntity.noContent().build();
     }
    // Notification processed successfully
    @PostMapping("/process")
    public ResponseEntity<String> processedNextNotification(){

         notificationProcessor.processNextNotification();

         return ResponseEntity.ok(
                 "Notification processed successfully"
         );
    }
}
