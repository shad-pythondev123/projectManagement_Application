package com.example.projectManagementSystem.Repository;

import com.example.projectManagementSystem.Model.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, String> {

}
