package com.vetcontrol.dto;

import com.vetcontrol.entity.TipoMovimiento;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoInventarioDTO {

    private Long id;

    @NotNull(message = "Debe seleccionar un producto")
    private Long productoId;

    @NotNull(message = "Debe seleccionar el tipo de movimiento")
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    private String descripcion;
}
