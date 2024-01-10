package com.example.projectManagementSystem.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class SprintRequestDto {
    String name;
    LocalDate startDate;
    LocalDate finishDate;
    String projectId;
}
