package com.example.projectManagementSystem.Repository;

import com.example.projectManagementSystem.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    @Query("select p from Project p where p.userId= :userId")
    List<Project> findByUserId(@Param("userId") Long userId);
}
