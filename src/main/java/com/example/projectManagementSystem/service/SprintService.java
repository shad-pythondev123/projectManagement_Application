package com.example.projectManagementSystem.service;

import com.example.projectManagementSystem.constant.Constants;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.dto.SprintRequestDto;
import com.example.projectManagementSystem.model.Project;
import com.example.projectManagementSystem.model.Sprint;
import com.example.projectManagementSystem.repository.ProjectRepository;
import com.example.projectManagementSystem.repository.SprintRepository;
import com.example.projectManagementSystem.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    ProjectRepository projectRepository;

    public ResponseDto createSprint(SprintRequestDto sprintRequestDto) {
        ResponseDto responseDto= new ResponseDto();
        Sprint sprint= sprintRepository.findByName(sprintRequestDto.getName()).orElse(null);
        Optional<Project> project= Optional.of(projectRepository.getById(sprintRequestDto.getProjectId()));
        if(project.isEmpty()){
            responseDto.setStatusCode(Constants.FAILED_CODE);
            responseDto.setStatus(Constants.FAILED);
            responseDto.setMessage("Project entered is not found!");
            return responseDto;
        }
        if(sprint==null){
            Sprint sprint1= new Sprint();
            sprint1.setName(sprintRequestDto.getName());
            sprint1.setCreatedDate(LocalDate.now());
            sprint1.setProject(project.get());
            Sprint savedSprint= sprintRepository.save(sprint1);
            responseDto.setMessage("Sprint added successfully");
            responseDto.setData(savedSprint);
            return responseDto;
        }
        responseDto.setStatusCode(Constants.FAILED_CODE);
        responseDto.setStatus(Constants.FAILED);
        responseDto.setMessage("Sprint already exists!");
        return responseDto;
    }
}
