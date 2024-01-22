package com.example.projectManagementSystem.controller;

import com.example.projectManagementSystem.dto.ResponseDto;
import com.example.projectManagementSystem.dto.SprintRequestDto;
import com.example.projectManagementSystem.service.SprintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/sprint")
@Slf4j
public class SprintController {
    @Autowired
    SprintService sprintService;
    @PostMapping("/create-sprint")
    public ResponseDto createSprint(@RequestBody SprintRequestDto sprintRequestDto){
        return sprintService.createSprint(sprintRequestDto);
    }
}
