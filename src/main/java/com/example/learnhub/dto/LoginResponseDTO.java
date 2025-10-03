package com.example.learnhub.dto;

import com.example.learnhub.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private Role role;
    private Long expiresIn; // em milissegundos
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
