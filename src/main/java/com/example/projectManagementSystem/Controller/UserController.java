package com.example.projectManagementSystem.Controller;

import com.example.projectManagementSystem.Dto.RequestDto.UserRequestDto;
import com.example.projectManagementSystem.Dto.ResponseDto.UserResponseDto;
import com.example.projectManagementSystem.Model.User;
import com.example.projectManagementSystem.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto){
        log.info("Entering addUser {}",userRequestDto.toString());
        try{
            UserResponseDto user= userService.addUser(userRequestDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){

            return new ResponseEntity<>("User already Present!", HttpStatus.ALREADY_REPORTED);
        }
    }
    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers(){
        log.info("Entering addUser {}");
        List<User> users= userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam Long id, @RequestParam String password){
        log.info("Entering updatePassword {}");
        try{
            String s= userService.updatePassword(id, password);
            return new ResponseEntity<>(s, HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity<>("User not Present!", HttpStatus.NOT_FOUND);
        }
    }
//    todo- updateUser, updatePassword, getUsers,

}
