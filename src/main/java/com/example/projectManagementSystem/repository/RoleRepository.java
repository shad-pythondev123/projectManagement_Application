package com.example.projectManagementSystem.repository;

import com.example.projectManagementSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r FROM Role r WHERE r.lowerRoleName = :lowerRoleName")
    Role findRoleByLowerRoleName(@Param("lowerRoleName") String lowerRoleName);
}
