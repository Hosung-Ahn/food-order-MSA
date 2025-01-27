package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.valueobject.Greeting;
import com.example.userservice.valueobject.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final Greeting greeting;
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/health")
    public String health() {
        return "User Service is up and running";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/user")
    public String createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = userMapper.userCreateRequestToUserDto(userCreateRequest);
        userService.createUser(userDto);
        return "User created successfully";
    }

}
