package com.campus.service.mapper;

import com.campus.service.dto.UserRequestDto;
import com.campus.service.dto.UserResponseDto;
import com.campus.service.entity.User;

import java.time.LocalDateTime;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public  static User mapToEntity(UserRequestDto dto){
            return  User.builder()
                    .name(dto.getName())
                    .mail(dto.getEmail())
                    .phoneNumber(dto.getPhoneNumber())
            .build();
    }

    public static  UserResponseDto mapToResponse(User user)
    {
        return UserResponseDto.builder().
                id(user.getId()).
                name(user.getName())
                .email(user.getMail()).
               phoneNumber(user.getPhoneNumber()).
                createdAt(user.getCreatedAt()).
                updateAt(user.getUpdatedAt())




            .build();
    }


}

