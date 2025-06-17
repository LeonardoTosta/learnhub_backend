package com.example.learnhub.model;

import com.example.learnhub.dto.UserDTO;
import com.example.learnhub.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class User {

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

    @PrePersist
    protected void createCreationDate() {
        this.createdAt = LocalDateTime.now();
    }

    public UserDTO toDto() {
        return UserDTO.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .build();
    }
}
