package com.example.projectManagementSystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String phone;
    private String pitcherUrl;
    private String country;
    private String city;
    private String pinCode;
    private String otp;
    private LocalDate otpExpiry;
    private String firstname;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String lowerRoleName;
}
