//package com.example.projectManagementSystem.controller;
//
//import com.example.projectManagementSystem.dto.AuthenticationRequest;
//import com.example.projectManagementSystem.dto.AuthenticationResponse;
//import com.example.projectManagementSystem.service.jwt.UserDetailsServiceImpl;
//import com.example.projectManagementSystem.utility.JwtUtil;
//import io.jsonwebtoken.io.IOException;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//    @PostMapping("/authentication")
//    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException {
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//        }catch(BadCredentialsException ex){
//            throw new BadCredentialsException("Email or Password Incorrect!");
//        }catch (DisabledException disabledException){
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Register User First");
//            return null;
//        }
//        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        final String jwt= jwtUtil.generateToken(userDetails.getUsername());
//        return new AuthenticationResponse(jwt);
//    }
//}
