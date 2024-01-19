package com.example.projectManagementSystem.service;

import com.example.projectManagementSystem.dto.AuthenticationRequest;
import com.example.projectManagementSystem.dto.AuthenticationResponse;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.dto.SignupRequest;
import com.example.projectManagementSystem.model.Role;
import com.example.projectManagementSystem.model.User;
import com.example.projectManagementSystem.repository.RoleRepository;
import com.example.projectManagementSystem.repository.UserRepository;
import com.example.projectManagementSystem.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public ResponseDto createUser(SignupRequest signupRequest) {
        ResponseDto responseDto= new ResponseDto();
        User user= new User();
        user.setFirstname(signupRequest.getFirstname());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setCity(signupRequest.getCity());
        user.setEmail(signupRequest.getEmail());
        user.setCountry(signupRequest.getCountry());
        user.setPhone(signupRequest.getPhone());
        user.setPitcherUrl(signupRequest.getPitcherUrl());
        user.setOtp(signupRequest.getOtp());
        user.setPinCode(signupRequest.getPinCode());
        user.setOtpExpiry(signupRequest.getOtpExpiry());
        user.setLastName(signupRequest.getLastName());
        user.setUsername(signupRequest.getUsername());
        Optional<Role> role= Optional.ofNullable(roleRepository.findRoleByLowerRoleName(signupRequest.getLowerRoleName()));
        user.setRole(role.get());

        Optional<User> uniqueUser= Optional.ofNullable(userRepository.getUserByUserName(user.getUsername()));
        Optional<User> userWithUniqueEmail= Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        responseDto.setData(user);
        if(user.getFirstname()==null || user.getFirstname().isEmpty()){
            responseDto.setMessage(Constants.FIRST_NAME_NOT_FOUND);
            responseDto.setStatusCode(Constants.FIRST_NAME_NOT_FOUND_CODE);
            responseDto.setStatus(Constants.FAILED);
            return responseDto;
        } else if (user.getLastName()==null || user.getLastName().isEmpty()) {
            responseDto.setMessage(Constants.LAST_NAME_NOT_FOUND);
            responseDto.setStatusCode(Constants.LAST_NAME_NOT_FOUND_code);
            responseDto.setStatus(Constants.FAILED);
            return responseDto;
        } else if (uniqueUser.isPresent()) {
            responseDto.setMessage(Constants.USERNAME_NOT_UNIQUE);
            responseDto.setStatusCode(Constants.USERNAME_NOT_UNIQUE_CODE);
            responseDto.setStatus(Constants.FAILED);
            return responseDto;
        } else if (userWithUniqueEmail.isPresent()) {
            responseDto.setMessage(Constants.EMAIL_NOT_UNIQUE);
            responseDto.setStatusCode(Constants.EMAIL_NOT_UNIQUE_CODE);
            responseDto.setStatus(Constants.FAILED);
            return responseDto;
        }else {
            User savedUser=userRepository.save(user);
            responseDto.setData(savedUser);
            responseDto.setMessage("User Successfully Created");
        }
       return responseDto;
    }

    public AuthenticationResponse generateResponse(AuthenticationRequest authenticationRequest) {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(authenticationRequest.getEmail()));
        AuthenticationResponse authenticationResponse= new AuthenticationResponse();
        authenticationResponse.setUser(user.get());
        return authenticationResponse;
    }


    public ResponseDto updatePassword(SignupRequest signupRequest) {
        Optional<User> user= Optional.ofNullable(userRepository.getUserByUserName(signupRequest.getUsername()));
        user.get().setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User savedUser= userRepository.save(user.get());
        ResponseDto responseDto= new ResponseDto();
        responseDto.setMessage("Password updated Successfully");
        responseDto.setData(savedUser);
        return responseDto;
    }


    public ResponseDto checkIfUsernameExists(SignupRequest signupRequest) {
        ResponseDto responseDto= new ResponseDto();
        if(signupRequest.getUsername()!=null){
            Optional<User> user= Optional.ofNullable(userRepository.getUserByUserName(signupRequest.getUsername()));
            responseDto.setData(user.get());
            responseDto.setMessage("Yes! The User With Given Username Exists");
            } else if (signupRequest.getEmail()!=null) {
            Optional<User> user= Optional.ofNullable(userRepository.findByEmail(signupRequest.getEmail()));
            responseDto.setData(user.get());
            responseDto.setMessage("Yes! The User With Given Email Exists");
            }
            else {
            responseDto.setStatusCode(Constants.USER_NOT_FOUND_CODE);
            responseDto.setStatus(Constants.FAILED);
            responseDto.setMessage("User Not Found!");
            responseDto.setData(signupRequest);
            }
            return responseDto;
    }


    public List<User> fetchUsers(SignupRequest signupRequest) {
        if(signupRequest.getUsername()==null) signupRequest.setUsername("");
        if(signupRequest.getFirstname()==null) signupRequest.setFirstname("");
        if(signupRequest.getLastName()==null) signupRequest.setLastName("");
        if(signupRequest.getPhone()==null) signupRequest.setPhone("");
        if(signupRequest.getEmail()==null) signupRequest.setEmail("");
        List<User> users = userRepository.getAllUsersByEmailContaining(signupRequest.getUsername(),
                signupRequest.getEmail(),signupRequest.getFirstname(),signupRequest.getLastName(),
                signupRequest.getPhone());
        return users;
    }

}
