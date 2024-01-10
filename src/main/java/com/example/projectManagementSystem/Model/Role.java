package com.example.projectManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String rolesId;
    private String rolename;
    private String lowerRoleName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "role")
    private User user;
}
