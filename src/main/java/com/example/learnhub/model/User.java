package com.example.learnhub.model;

import com.example.learnhub.dto.UserDTO;
import com.example.learnhub.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 40)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @PrePersist
    protected void createCreationDate() {
        this.createdAt = LocalDateTime.now();
    }

    public UserDTO toDto() {
        return UserDTO.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .username(this.username)
                .build();
    }
}
