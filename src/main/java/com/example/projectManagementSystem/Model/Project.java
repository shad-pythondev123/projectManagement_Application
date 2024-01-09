package com.example.projectManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String description;
    LocalDate startDate;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<User> admins= new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Sprint> sprints= new ArrayList<>();
}
