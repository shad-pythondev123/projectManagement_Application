package com.example.projectManagementSystem.Service;

import com.example.projectManagementSystem.Dto.RequestDto.UserRequestDto;
import com.example.projectManagementSystem.Dto.ResponseDto.UserResponseDto;
import com.example.projectManagementSystem.Model.Project;
import com.example.projectManagementSystem.Model.User;
import com.example.projectManagementSystem.Repository.ProjectRepository;
import com.example.projectManagementSystem.Repository.UserRepository;
import com.example.projectManagementSystem.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user= UserTransformer.UserRequestDtoToUser(userRequestDto);
        Optional<Project> project= projectRepository.findById(user.getProjectId());
        user.setProject(project.get());
        User savedUser= userRepository.save(user);
        UserResponseDto user1= UserTransformer.UsertoUserResponseDto(savedUser);
        return user1;

    }

    public List<User> getUsers() {
        List<User> listOfUsers=userRepository.findAll();
        return listOfUsers;
    }

    public String updatePassword(Long id, String password) throws Exception{
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("User Doesn't Exist!");
        }
        User newUser= user.get();
        newUser.setPassword(password);
        userRepository.save(newUser);
        return"successfully updated!";
    }
}
