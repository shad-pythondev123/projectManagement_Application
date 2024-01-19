package com.example.projectManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProjectUser {
    @EmbeddedId
    private ProjectUserId id;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
