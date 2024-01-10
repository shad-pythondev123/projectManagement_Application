package com.example.projectManagementSystem.Transformer;

import com.example.projectManagementSystem.Dto.RequestDto.UserRequestDto;
import com.example.projectManagementSystem.Dto.ResponseDto.UserResponseDto;
import com.example.projectManagementSystem.Model.User;

public class UserTransformer {
    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .firstname(userRequestDto.getFirstName())
                .lastname(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
    }
    public static UserResponseDto UsertoUserResponseDto(User user){
        return UserResponseDto.builder()
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }
}
