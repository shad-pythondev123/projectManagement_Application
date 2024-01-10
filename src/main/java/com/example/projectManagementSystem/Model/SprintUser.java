package com.example.projectManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SprintUser {
    @Id
    private String sprintId;
    private Long userId;
    private LocalDate createdDate;
}
