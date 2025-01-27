package com.example.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private String encryptedPassword;
    private LocalDateTime createdAt;
}
