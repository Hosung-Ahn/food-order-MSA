package com.example.userservice.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 100)
    private String encryptedPassword;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public UserEntity(String email, String name, String userId, String encryptedPassword, LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptedPassword = encryptedPassword;
        this.createdAt = createdAt;
    }
}
