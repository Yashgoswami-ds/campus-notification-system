package com.campus.service.dto;

import com.campus.service.entity.NotificationPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    @NotNull(message="user id Required.")
    private  Long UserId;
    @NotBlank(message = "Title is required.")
    @Size(max = 200)
     private  String title;
    @NotBlank(message = "Msg is required.")
    private  String message;
    @NotNull(message = "priority is required.")
    private NotificationPriority priority;





}
