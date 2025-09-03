package com.example.learnhub.controller;


import com.example.learnhub.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.learnhub.services.UserService;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping(path = "/registerUser")
    public ResponseEntity<Void> registerUser (@Valid @RequestBody UserDTO userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/editUser")
    public ResponseEntity<Void> editUser (@RequestBody UserDTO userDto) {
        userService.editUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/listUsers")
    public ResponseEntity<?> listUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.findAllUsers(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/findUserbyId/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userService.findUserDtoById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

