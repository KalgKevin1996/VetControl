package com.vetcontrol.config;

import com.vetcontrol.entity.Rol;
import com.vetcontrol.entity.Usuario;
import com.vetcontrol.repository.RolRepository;
import com.vetcontrol.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {

            // Crear roles si no existen
            Rol adminRol = rolRepository.findByNombre("ROLE_ADMIN")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("ROLE_ADMIN").build()));

            Rol operadorRol = rolRepository.findByNombre("ROLE_OPERADOR")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("ROLE_OPERADOR").build()));

            // Crear usuario ADMIN si no existe
            if (!usuarioRepository.existsByEmail("admin@vetcontrol.com")) {
                Usuario admin = Usuario.builder()
                        .nombre("Admin")
                        .apellido("Vet")
                        .email("admin@vetcontrol.com")
                        .password(passwordEncoder.encode("admin123"))
                        .habilitado(true)
                        .roles(Set.of(adminRol))
                        .build();
                usuarioRepository.save(admin);
            }

            // Crear usuario OPERADOR si no existe
            if (!usuarioRepository.existsByEmail("operador@vetcontrol.com")) {
                Usuario operador = Usuario.builder()
                        .nombre("Operador")
                        .apellido("Inventario")
                        .email("operador@vetcontrol.com")
                        .password(passwordEncoder.encode("operador123"))
                        .habilitado(true)
                        .roles(Set.of(operadorRol))
                        .build();
                usuarioRepository.save(operador);
            }

            System.out.println("✅ Datos iniciales insertados.");
        };
    }
}
