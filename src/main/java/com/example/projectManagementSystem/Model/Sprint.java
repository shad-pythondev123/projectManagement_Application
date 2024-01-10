package com.example.projectManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String projectId;
    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;
}
