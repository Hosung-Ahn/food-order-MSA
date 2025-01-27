package com.example.userservice.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, max = 100, message = "Email must be between 2 and 100 characters")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
}
