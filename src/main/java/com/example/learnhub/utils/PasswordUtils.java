package com.example.learnhub.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashDoBanco = "$2a$10$Cs4w1L/Wz.Lw8oyrwR3Oc.QanyU6emqJGPcFkEC2v.5j8EhjpwQXm";

        boolean ok = passwordEncoder.matches("Doiwannaknow@1", hashDoBanco);
        System.out.println(ok);  //
    }
}
