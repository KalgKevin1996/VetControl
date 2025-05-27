package com.vetcontrol.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        String rawPassword = "admin123"; // prueba aquí con la contraseña que creés correcta
        String hash = "$2a$10$1hY4olyYoJyHiQHeKn4KyOOXMC6uIadmaoziZjAwoB0.E8L9kaIsS";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean match = encoder.matches(rawPassword, hash);
        System.out.println("¿Coincide? " + match);
    }
}
