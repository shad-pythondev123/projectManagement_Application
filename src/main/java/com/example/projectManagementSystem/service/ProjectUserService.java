package com.example.projectManagementSystem.service;

import com.example.projectManagementSystem.constant.Constants;
import com.example.projectManagementSystem.dto.ProjectUserRequest;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.model.Project;
import com.example.projectManagementSystem.model.ProjectUser;
import com.example.projectManagementSystem.model.ProjectUserId;
import com.example.projectManagementSystem.model.User;
import com.example.projectManagementSystem.repository.ProjectRepository;
import com.example.projectManagementSystem.repository.ProjectUserRepository;
import com.example.projectManagementSystem.repository.UserRepository;
import com.example.projectManagementSystem.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectUserService {
    @Autowired
    ProjectUserRepository projectUserRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseDto addProjectUser(ProjectUserRequest projectUserRequest) {
        ResponseDto responseDto= new ResponseDto();
        Utility utility= new Utility();
        String userName= utility.getUserName();
        Project project= projectRepository.getById(projectUserRequest.getProjectId());
        if(project==null){
            responseDto.setMessage("Project not Found!");
            responseDto.setStatusCode(Constants.FAILED_CODE);
            responseDto.setStatus(Constants.FAILED);
            responseDto.setData(project);
            return responseDto;
        }
        Optional<User> user= userRepository.findById(projectUserRequest.getUserId());
        if(user.isEmpty()){
            responseDto.setMessage(Constants.USER_NOT_FOUND);
            responseDto.setStatusCode(Constants.USERNAME_NOT_UNIQUE_CODE);
            responseDto.setStatus(Constants.USER_NOT_FOUND);
            responseDto.setData(user);
            return responseDto;
        }
        ProjectUser projectUser1= projectUserRepository.findByProjectId(projectUserRequest.getProjectId()).orElse(null);
        //use utilityclass to get and validate username and project admin.
        //search projectUser if exists with submittedbyId as userId, projectId, isAdmin==true.
        Optional<User> user1= Optional.ofNullable(userRepository.findByEmail(userName));
        if(project.getCreatedBy()==user1.get().getId()) {
            if (projectUser1 != null) {
                ProjectUser projectUser = new ProjectUser();
                projectUser.setAdmin(projectUserRequest.isAdmin());
                ProjectUserId projectUserId = new ProjectUserId();
                projectUserId.setProjectId(project.getId());
                projectUserId.setUserId(user.get().getId());
                projectUser.setId(projectUserId);
                projectUser.setCreatedDate(LocalDate.now());
                ProjectUser savedProjectUser = projectUserRepository.save(projectUser);
                responseDto.setMessage("User added to Project");
                responseDto.setData(savedProjectUser);
                return responseDto;
            }
        }
        responseDto.setMessage("Failed to add user");
        responseDto.setStatusCode(Constants.FAILED_CODE);
        responseDto.setStatus(Constants.FAILED);
        responseDto.setData(null);
        return responseDto;
    }
    public ResponseDto getProjectFromUserId(Long userId){
        ResponseDto responseDto= new ResponseDto();
        List<ProjectUser> projectUsers= projectUserRepository.getAllById(userId);
        List<String> projects= new ArrayList<>();
        for(ProjectUser p:projectUsers){
            projects.add(p.getId().getProjectId());
        }
        List<Project> projects1= projectRepository.findProjectsByIds(projects);
        responseDto.setData(projects1);
        responseDto.setMessage("Projects");
        return responseDto;
    }
}
