package com.example.projectManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roleId;

    @Column(name = "role_name")
    private String roleName;
    @Column(name = "lower_role_name")
    private String lowerRoleName;
    @Column(name = "description")
    private String description;

}
