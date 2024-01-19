package com.example.projectManagementSystem.dto;

import com.example.projectManagementSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private User user;
    private String token;
}
