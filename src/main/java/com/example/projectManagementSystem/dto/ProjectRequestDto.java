package com.example.projectManagementSystem.dto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    private String name;
    private String description;
    private Long userId;
}
