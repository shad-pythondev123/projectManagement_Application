package com.example.projectManagementSystem.controller;

import com.example.projectManagementSystem.dto.AuthenticationRequest;
import com.example.projectManagementSystem.dto.AuthenticationResponse;
import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.dto.SignupRequest;
import com.example.projectManagementSystem.model.User;
import com.example.projectManagementSystem.service.UserServiceImpl;
import com.example.projectManagementSystem.service.jwt.UserDetailsServiceImpl;
import com.example.projectManagementSystem.utility.JwtUtil;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/create-user")
    public ResponseDto createUser(@RequestBody SignupRequest signupRequest){
        log.info("Entering addUser {}",signupRequest.toString());

         return    userService.createUser(signupRequest);
    }
    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException {
        try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Email or Password Incorrect!");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Register User First");
            return null;
        }
        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt= jwtUtil.generateToken(userDetails.getUsername());
        AuthenticationResponse authenticationResponse= userService.generateResponse(authenticationRequest);
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
    @PostMapping("/update-password")
    public ResponseDto updatePassword(@RequestBody SignupRequest signupRequest){
        return userService.updatePassword(signupRequest);
    }
    @GetMapping("/retrieve")
    public ResponseDto checkIfUsernameExists(@RequestBody SignupRequest signupRequest){
        return userService.checkIfUsernameExists(signupRequest);
    }
    @PostMapping("/fetch")
    public List<User> fetchUsers(@RequestBody SignupRequest signupRequest){
        return userService.fetchUsers(signupRequest);
    }
    ////    todo- updateUser, updatePassword, getUsers, username/email exists, fetch()- fetch users based on email, username, firsname, lastname, phone

}

