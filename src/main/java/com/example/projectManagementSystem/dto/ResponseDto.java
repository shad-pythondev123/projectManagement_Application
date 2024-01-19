package com.example.projectManagementSystem.dto;

import com.example.projectManagementSystem.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String statusCode= Constants.SUCCESS_CODE;
    private String status= Constants.SUCCESS;
    private String message;
    private Object data;
}
