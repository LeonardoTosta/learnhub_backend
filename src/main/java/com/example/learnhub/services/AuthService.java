package com.example.learnhub.services;

import com.example.learnhub.dto.LoginRequestDTO;
import com.example.learnhub.dto.LoginResponseDTO;
import com.example.learnhub.model.User;
import com.example.learnhub.repository.UserRepository;
import com.example.learnhub.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        try {
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Busca o usuário no banco para pegar informações adicionais
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            // Gera o token JWT
            String token = jwtUtil.generateToken(
                    user.getUsername(),
                    user.getName(),
                    user.getRole().name()
            );

            return LoginResponseDTO.builder()
                    .token(token)
                    .username(user.getUsername())
                    .name(user.getName())
                    .role(user.getRole())
                    .expiresIn(jwtUtil.getExpirationTime())
                    .build();

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}

