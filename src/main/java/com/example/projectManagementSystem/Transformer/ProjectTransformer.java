package com.example.projectManagementSystem.Transformer;

import com.example.projectManagementSystem.Dto.RequestDto.ProjectRequestDto;
import com.example.projectManagementSystem.Dto.RequestDto.UserRequestDto;
import com.example.projectManagementSystem.Dto.ResponseDto.ProjectResponseDto;
import com.example.projectManagementSystem.Dto.ResponseDto.UserResponseDto;
import com.example.projectManagementSystem.Model.Project;
import com.example.projectManagementSystem.Model.User;

public class ProjectTransformer {
    public static Project ProjectRequestDtoToProject(ProjectRequestDto projectRequestDto){
        return Project.builder()
                .name(projectRequestDto.getName())
                .userId(projectRequestDto.getUserId())
                .startDate(projectRequestDto.getStartDate())
                .description(projectRequestDto.getDescription())
                .build();
    }
    public static ProjectResponseDto ProjecttoProjectResponseDto(Project project){
        return ProjectResponseDto.builder()
                .name(project.getName())
                .startDate(project.getStartDate())
                .id(project.getId())
                .build();
    }
}
