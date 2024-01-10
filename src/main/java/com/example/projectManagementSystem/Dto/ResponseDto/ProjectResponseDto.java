package com.example.projectManagementSystem.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponseDto {
    String id;
    String name;
    LocalDate startDate;
}
