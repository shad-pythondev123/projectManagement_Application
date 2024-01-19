package com.example.projectManagementSystem.controller;

import com.example.projectManagementSystem.dto.ProjectRequestDto;
import com.example.projectManagementSystem.dto.ProjectUserRequest;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project-user")
public class ProjectUserController {
    @Autowired
    ProjectUserService projectUserService;
    @PostMapping("/add-user")
    public ResponseDto addUser(@RequestBody ProjectUserRequest projectUserRequest){
        return projectUserService.addProjectUser(projectUserRequest);
    }
    @GetMapping("/get-projects")
    public ResponseDto getProjectFromUserId(@RequestParam Long userId){
        return projectUserService.getProjectFromUserId(userId);
    }

}
