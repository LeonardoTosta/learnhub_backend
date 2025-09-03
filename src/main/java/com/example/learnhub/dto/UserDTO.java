package com.example.learnhub.dto;

import com.example.learnhub.enums.Role;
import com.example.learnhub.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "A role não pode estar vazia ou conter apenas espaços")
    private Role role;

    @NotBlank(message = "Login inválido! Por favor insira um login válido")
    private String username;

    @NotBlank(message = "Senha inválida! Por favor insira uma senha válida")
    private String password;

    @JsonIgnore
    private LocalDateTime createdAt;

//    public User toModel() {
//        return User.builder()
//                .id(this.id)
//                .name(this.name)
//                .role(this.role)
//                .createdAt(this.createdAt)
//                .build();
//    }
}
