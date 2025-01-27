package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.valueobject.UserCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setName(userEntity.getName());
        userDto.setUserId(userEntity.getUserId());
        userDto.setEncryptedPassword(userEntity.getEncryptedPassword());
        userDto.setCreatedAt(userEntity.getCreatedAt());
        return userDto;
    }

    public UserEntity userDtoToUserEntity(UserDto userDto) {
        return new UserEntity(
                userDto.getEmail(),
                userDto.getName(),
                userDto.getUserId(),
                userDto.getEncryptedPassword(),
                userDto.getCreatedAt()
        );
    }

    public UserDto userCreateRequestToUserDto(UserCreateRequest userCreateRequest) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userCreateRequest.getEmail());
        userDto.setName(userCreateRequest.getName());
        userDto.setPassword(userCreateRequest.getPassword());
        return userDto;
    }
}
