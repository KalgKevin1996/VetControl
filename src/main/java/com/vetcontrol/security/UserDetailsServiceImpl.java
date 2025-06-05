package com.vetcontrol.security;

import com.vetcontrol.entity.Usuario; // Importa tu entidad Usuario
import com.vetcontrol.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Necesario para definir roles
import org.springframework.stereotype.Service;

import java.util.Collections; // Para crear una colección de una sola autoridad

@Service
@RequiredArgsConstructor // Genera un constructor con los campos 'final'
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscar el usuario en tu base de datos por el email (que es el 'username' de Spring Security)
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));

        // 2. Crear una instancia de org.springframework.security.core.userdetails.User
        //    usando los datos de tu entidad Usuario.
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),           // username (aquí es el email de tu Usuario)
                usuario.getPassword(),        // password
                usuario.isActivo(),           // enabled (usamos tu campo 'activo')
                true,                         // accountNonExpired (asumimos que no expira)
                true,                         // credentialsNonExpired (asumimos que no expiran)
                true,                         // accountNonLocked (asumimos que no se bloquea)
                // Autoridades/Roles: Spring Security espera una colección de GrantedAuthority.
                // Tu rol es un String, así que lo convertimos a SimpleGrantedAuthority,
                // prefijándolo con "ROLE_" como es la convención de Spring Security.
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }
}