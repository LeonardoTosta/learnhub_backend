package com.example.learnhub.dto;

import com.example.learnhub.enums.Role;
import com.example.learnhub.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar vazio ou conter apenas espaços")
    private String name;
    private Role role;

    @JsonIgnore
    private LocalDateTime createdAt;

    public User toModel() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .createdAt(this.createdAt)
                .build();
    }
}
