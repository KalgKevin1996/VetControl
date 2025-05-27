package com.vetcontrol.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double precio;

    @NotNull(message = "La cantidad es obligatoria")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    private String imagen;

    @NotNull(message = "Debe seleccionar una categoría")
    private Long categoriaId;

    private String categoriaNombre; // ✅ nuevo campo

    private boolean activo = true;
}