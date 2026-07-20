package com.campus.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campusUserData")
@Data
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)

    private  String phoneNumber;
    @Column(nullable = false ,updatable = false)
    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @PrePersist
    protected   void  onCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    protected  void  onUpdate(){
        updatedAt=LocalDateTime.now();
    }


}