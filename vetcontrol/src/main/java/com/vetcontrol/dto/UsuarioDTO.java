package com.vetcontrol.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private boolean activo;

    @ToString.Exclude // para que no se imprima accidentalmente
    private String confirmPassword;
}
