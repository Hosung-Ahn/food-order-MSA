package com.example.userservice.valueobject;

import lombok.Data;

@Data
public class UserCreateResponse {
    private String email;
    private String name;
    private String userId;
}
