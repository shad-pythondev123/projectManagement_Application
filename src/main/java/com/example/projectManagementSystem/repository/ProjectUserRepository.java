package com.example.projectManagementSystem.repository;

import com.example.projectManagementSystem.model.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, String> {
    @Query("SELECT p FROM ProjectUser p WHERE p.id.userId= :userId")
    List<ProjectUser> getAllById(@Param("userId") Long userId);
    @Query("SELECT p FROM ProjectUser p WHERE p.id.userId= :userId")
    Optional<ProjectUser> findByUserId(@Param("userId") Long userId);
    @Query("SELECT p FROM ProjectUser p WHERE p.id.projectId like :projectId")
    Optional<ProjectUser> findByProjectId(@Param("projectId") String projectId);
}
