package com.campus.service.service.impl;

import com.campus.service.dto.UserRequestDto;
import com.campus.service.dto.UserResponseDto;
import com.campus.service.entity.User;
import com.campus.service.mapper.UserMapper;
import com.campus.service.repository.UserRepository;
import com.campus.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        User user = UserMapper.mapToEntity(request);

        User savedUser = userRepository.save(user);

        return UserMapper.mapToResponse(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setName(requestDto.getName());
        user.setMail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        userRepository.delete(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return UserMapper.mapToResponse(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}