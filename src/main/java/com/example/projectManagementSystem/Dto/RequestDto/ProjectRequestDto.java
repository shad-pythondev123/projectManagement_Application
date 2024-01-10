package com.example.projectManagementSystem.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProjectRequestDto {
    String name;
    LocalDate startDate;
    String description;
    Long userId;
}
