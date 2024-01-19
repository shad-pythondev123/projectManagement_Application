package com.example.projectManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserRequest {
 private Long userId;
 private String projectId;
 private boolean isAdmin;
}
