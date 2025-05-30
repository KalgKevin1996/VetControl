package com.vetcontrol.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;

    @NotBlank(message = "Debe confirmar la contraseña")
    @ToString.Exclude
    private String confirmPassword;

    @NotBlank(message = "Debe seleccionar un rol")
    private String rol;

    private boolean activo;
}
