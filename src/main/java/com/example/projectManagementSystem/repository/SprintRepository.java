package com.example.projectManagementSystem.repository;

import com.example.projectManagementSystem.model.Sprint;
import com.example.projectManagementSystem.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, String> {
    @Query("SELECT s FROM Sprint s WHERE s.name like :name")
   Optional<Sprint> findByName(@Param("name") String name);
}
