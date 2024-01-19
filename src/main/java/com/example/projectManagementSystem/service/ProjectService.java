package com.example.projectManagementSystem.service;

import com.example.projectManagementSystem.constant.Constants;
import com.example.projectManagementSystem.dto.ProjectRequestDto;
import com.example.projectManagementSystem.dto.ResponseDto;
//import com.example.projectManagementSystem.model.ProjectUser;
import com.example.projectManagementSystem.model.Project;
import com.example.projectManagementSystem.model.ProjectUser;
import com.example.projectManagementSystem.model.ProjectUserId;
import com.example.projectManagementSystem.model.User;
import com.example.projectManagementSystem.repository.ProjectRepository;
//import com.example.projectManagementSystem.Repository.ProjectUserRepository;
//import com.example.projectManagementSystem.repository.ProjectUserRepository;
import com.example.projectManagementSystem.repository.ProjectUserRepository;
import com.example.projectManagementSystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectUserRepository projectUserRepository;

    public ResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Project project= new Project();
        ResponseDto responseDto= new ResponseDto();
        Project project1= projectRepository.findByProjectName(projectRequestDto.getName()).orElse(null);
        User user= userRepository.findById(projectRequestDto.getUserId()).orElse(null);
        if(user== null|| project1!=null){
            responseDto.setStatus(Constants.FAILED);
            if(project1!=null){
                responseDto.setMessage("Project already exists");
                responseDto.setStatusCode(Constants.FAILED_CODE);
            }else {
                responseDto.setMessage(Constants.USER_NOT_FOUND);
                responseDto.setStatusCode(Constants.USER_NOT_FOUND_CODE);
            }
            return responseDto;
        }

        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setCreatedBy(user.getId());
        project.setUpdatedBy(user.getId());
        project.setStartDate(LocalDate.now());
        project.setUpdatedDate(LocalDate.now());
        Project savedProject= projectRepository.save(project);
        ProjectUser projectUser= new ProjectUser();
        ProjectUserId projectUserId= new ProjectUserId();
        projectUserId.setProjectId(savedProject.getId());
        projectUserId.setUserId(user.getId());
        projectUser.setId(projectUserId);
        projectUser.setAdmin(true);
        projectUserRepository.save(projectUser);
//        todo - add projectUser in projectUser Table with isadmin==true.
        responseDto.setMessage(Constants.SUCCESS);
        responseDto.setData(savedProject);
        return responseDto;
    }

    public ResponseDto updateProject(ProjectRequestDto projectRequestDto) {
        Project project= projectRepository.findByProjectName(projectRequestDto.getName()).orElse(null);
        ResponseDto responseDto= new ResponseDto();
        if(project==null){
            responseDto.setMessage("Project not Found!");
            responseDto.setStatusCode(Constants.FAILED_CODE);
            responseDto.setStatus(Constants.FAILED);
            responseDto.setData(projectRequestDto);
            return responseDto;
        }
        User user= userRepository.findById(projectRequestDto.getUserId()).orElse(null);
        if(user==null){
            responseDto.setMessage(Constants.USER_NOT_FOUND);
            responseDto.setStatusCode(Constants.USERNAME_NOT_UNIQUE_CODE);
            responseDto.setStatus(Constants.USER_NOT_FOUND);
            responseDto.setData(projectRequestDto);
            return responseDto;
        }
        project.setUpdatedBy(user.getId());
        project.setDescription(projectRequestDto.getDescription());
        project.setUpdatedDate(LocalDate.now());
        Project savedProject=projectRepository.save(project);
        responseDto.setMessage(Constants.SUCCESS);
        responseDto.setData(savedProject);
        return responseDto;
    }
}
