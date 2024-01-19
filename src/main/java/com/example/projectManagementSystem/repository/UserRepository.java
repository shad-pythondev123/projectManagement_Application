package com.example.projectManagementSystem.repository;

import com.example.projectManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email like :email")
    User findByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.username like :username")
    User getUserByUserName(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email like %:email% AND u.username like %:username% AND u.firstname like %:firstname% AND u.lastName like %:lastName% AND u.phone like %:phone%")
    List<User> getAllUsersByEmailContaining
            (@Param("username") String username,
             @Param("email") String email,
             @Param("firstname") String firstname,
             @Param("lastName") String lastName,
             @Param("phone") String phone);
}
