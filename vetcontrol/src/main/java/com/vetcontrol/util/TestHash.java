package com.vetcontrol.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        String rawPassword = "123456789"; // prueba aquí con la contraseña que creés correcta
        String hash = "$2a$10$Q/4umObWnfF0C3VAVod7te6N/AtMnzHesdCCw6Qkraoz7X.UuO2v2";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean match = encoder.matches(rawPassword, hash);
        System.out.println("¿Coincide? " + match);
    }
}
