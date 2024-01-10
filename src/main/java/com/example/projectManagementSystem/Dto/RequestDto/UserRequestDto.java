package com.example.projectManagementSystem.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String projectId;
    private String password;
}
