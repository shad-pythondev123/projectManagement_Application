//package com.example.projectManagementSystem.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.time.LocalDate;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Sprint {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//    private String name;
//    private LocalDate startDate;
//    private LocalDate finishDate;
//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;
//}
