package com.example.projectManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;

    @Column(name = "last_name")
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private String projectId;
    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;


}
