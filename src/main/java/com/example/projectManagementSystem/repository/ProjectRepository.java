package com.example.projectManagementSystem.repository;

import com.example.projectManagementSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE p.name like :name")
    Optional<Project> findByProjectName(@Param("name") String name);

    @Query("SELECT p FROM Project p WHERE p.id IN :projectIds")
    List<Project> findProjectsByIds(@Param("projectIds") List<String> projectIds);
}
//return list of projects where id is in listOfStrings
