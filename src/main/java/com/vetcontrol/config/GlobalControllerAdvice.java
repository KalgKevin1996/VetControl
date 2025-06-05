package com.vetcontrol.config;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.Principal;
import java.util.Collection;

@Hidden
@ControllerAdvice
public class GlobalControllerAdvice {


    @ModelAttribute("usuarioActual")
    public Principal usuarioActual(Principal principal) {
        return principal;
    }

    @ModelAttribute("usuarioRol")
    public String obtenerRol(Authentication auth) {
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (!authorities.isEmpty()) {
                return authorities.iterator().next().getAuthority(); // Ej: "ROLE_ADMIN"
            }
        }
        return "SIN_ROL";
    }
}
