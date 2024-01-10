package com.example.projectManagementSystem.Controller;

import com.example.projectManagementSystem.Dto.RequestDto.ProjectRequestDto;
import com.example.projectManagementSystem.Model.Project;
import com.example.projectManagementSystem.Service.ProjectService;
import com.example.projectManagementSystem.Transformer.ProjectTransformer;
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
  public ResponseEntity<?> addProject(@RequestBody ProjectRequestDto projectRequestDto){
    log.info("Entering add-project {}", projectRequestDto.toString());
    try{
      projectService.addProject(projectRequestDto);
      return new ResponseEntity<>("Project Created", HttpStatus.CREATED);
    }catch(RuntimeException e){
       return new ResponseEntity<>("Failed! try again", HttpStatus.NOT_FOUND);
    }

  }
  @GetMapping("/get-projects-by-userId")
  public ResponseEntity<List<Project>> getProjectsByUserId(@RequestParam Long userId){
    log.info("Entering get-projects-by-userId {}");
    List<Project> list= projectService.getProjectByUserId(userId);
    return new ResponseEntity<>(list,HttpStatus.OK);
  }
  @PutMapping("/update-project")
  public ResponseEntity<String> updateProject(@RequestParam String id,@RequestParam LocalDate startDate,@RequestParam String description){
    log.info("Entering update-project {}");
    try{
      String s=projectService.updateProject(id, startDate, description);
      return new ResponseEntity<>(s, HttpStatus.CREATED);
    }catch(Exception e){
      return new ResponseEntity<>("No projects for user", HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping
  public ResponseEntity<String> addUser(@RequestParam Long userId, @RequestParam String projectId){
    String s= projectService.addUser(userId,projectId);
    return new ResponseEntity<>(s, HttpStatus.CREATED);
  }

//  todo- getProject, updateProject, addUser, removeUser
}
