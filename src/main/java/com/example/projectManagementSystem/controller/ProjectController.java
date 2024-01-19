package com.example.projectManagementSystem.controller;

import com.example.projectManagementSystem.dto.ProjectRequestDto;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.model.Project;
import com.example.projectManagementSystem.service.ProjectService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
@Slf4j
public class ProjectController {
  @Autowired
  ProjectService projectService;


  @PostMapping("/add-project")
  public ResponseDto addProject(@RequestBody ProjectRequestDto projectRequestDto){
    log.info("Entering add-project {}", projectRequestDto.toString());
    return projectService.addProject(projectRequestDto);
  }


  @PostMapping("/update")
  public ResponseDto updateProject(@RequestBody ProjectRequestDto projectRequestDto){
    log.info("Entering update-project {}", projectRequestDto.toString());
    return projectService.updateProject(projectRequestDto);
  }

//  @PostMapping("/delete-user")
//  public ResponseDto deleteUser(@RequestBody ProjectRequestDto projectRequestDto){
//    return projectService.deleteUser(projectRequestDto);
//  }

//  todo- getProject, updateProject, addUser, removeUser
//  todo- validateNAME,
}
