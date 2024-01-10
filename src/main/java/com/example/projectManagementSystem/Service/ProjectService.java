package com.example.projectManagementSystem.Service;

import com.example.projectManagementSystem.Dto.RequestDto.ProjectRequestDto;
import com.example.projectManagementSystem.Model.Project;
import com.example.projectManagementSystem.Model.ProjectUser;
import com.example.projectManagementSystem.Model.User;
import com.example.projectManagementSystem.Repository.ProjectRepository;
import com.example.projectManagementSystem.Repository.ProjectUserRepository;
import com.example.projectManagementSystem.Repository.UserRepository;
import com.example.projectManagementSystem.Transformer.ProjectTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectUserRepository projectUserRepository;

    public void addProject(ProjectRequestDto projectRequestDto) {
        Project project= ProjectTransformer.ProjectRequestDtoToProject(projectRequestDto);
        Optional<User> user = userRepository.findById(projectRequestDto.getUserId());

        projectRepository.save(project);
//        user.get().setProject(project);
        userRepository.save(user.get());
    }

    public List<Project> getProjectByUserId(Long userId) {
        List<Project> projects= projectRepository.findByUserId(userId);
        return projects;
    }

    public String updateProject(String id, LocalDate startDate, String description) throws Exception{
        Optional<Project> project= projectRepository.findById(id);
        if(project.isEmpty()) throw new Exception("Project not found for this user");
        Project p= project.get();
        p.setDescription(description);
        p.setStartDate(startDate);
        projectRepository.save(p);
        return "Success";
    }

    public String addUser(Long userId, String projectId) {
        Optional<Project> project= projectRepository.findById(projectId);
        ProjectUser projectUser= new ProjectUser();
        projectUser.setAdmin(true);
        projectUser.setProjectId(project.get().getId());
        projectUser.setCreatedDate(project.get().getStartDate());
        projectUser.setUserId(userId);
        projectUserRepository.save(projectUser);
        return "Successfully Added!";
    }
}
