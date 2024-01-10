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
public class ProjectUser {
    @Id
    private String projectId;
    private Long userId;
    private boolean isAdmin;
    private LocalDate createdDate;
}
