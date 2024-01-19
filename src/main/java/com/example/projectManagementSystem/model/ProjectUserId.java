package com.example.projectManagementSystem.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ProjectUserId implements Serializable {
    private String projectId;
    private Long userId;
}
