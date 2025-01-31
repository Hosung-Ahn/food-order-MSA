package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.valueobject.Greeting;
import com.example.userservice.valueobject.UserCreateRequest;
import com.example.userservice.valueobject.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final Greeting greeting;
    private final UserService userService;
    private final UserMapper userMapper;
    private final Environment env;

    @GetMapping("/health")
    public String health() {
        return "User Service is up and running" + ", port: " + env.getProperty("server.port") + ", test: " + env.getProperty("test.hello");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = userMapper.userCreateRequestToUserDto(userCreateRequest);
        UserDto user = userService.createUser(userDto);
        UserCreateResponse userCreateResponse = userMapper.userDtoToUserCreateResponse(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCreateResponse);
    }

}
