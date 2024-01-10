package com.example.projectManagementSystem.Service;

import com.example.projectManagementSystem.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;

}
