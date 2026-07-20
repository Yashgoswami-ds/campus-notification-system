package com.campus.service.service;

import com.campus.service.dto.UserRequestDto;
import com.campus.service.dto.UserResponseDto;

import java.util.List;

public interface UserService {
        UserResponseDto createUser(UserRequestDto request);
        UserResponseDto updateUser(Long id , UserRequestDto requestDto);
        void  deleteUser(Long id);
        UserResponseDto getUserById(Long id);
         List<UserResponseDto >getAllUsers();
}
