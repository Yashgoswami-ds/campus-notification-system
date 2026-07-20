package com.campus.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campus_notifications")
@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
  private String title;
    @Column(nullable = false, length = 1000)
  private  String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false )
   private  NotificationPriority priority;



    @Enumerated(EnumType.STRING)
    @Column(nullable  = false)
   private  NotificationStatus status;


    @Column(nullable  = false)
   private boolean isRead;


    @Column(nullable = false,updatable = false)
   private LocalDateTime createdAt;

    private LocalDateTime  updatedAt;

    @PrePersist
    public  void  onCreated(){
        createdAt=  LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    public  void  onUpdate(){
        updatedAt=LocalDateTime.now();
    }

}
