package com.vetcontrol.controller.rest;

import com.vetcontrol.dto.MovimientoInventarioDTO;
import com.vetcontrol.service.MovimientoInventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimientos", description = "Entradas y salidas de inventario")
public class MovimientoRestController {

    private final MovimientoInventarioService movimientoService;

    @GetMapping
    @Operation(summary = "Listar todos los movimientos de inventario")
    public ResponseEntity<List<MovimientoInventarioDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.listarTodos());
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Listar movimientos por ID de producto")
    public ResponseEntity<List<MovimientoInventarioDTO>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(movimientoService.listarPorProducto(productoId));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo movimiento (entrada o salida)")
    public ResponseEntity<MovimientoInventarioDTO> registrar(@RequestBody MovimientoInventarioDTO dto) {
        MovimientoInventarioDTO creado = movimientoService.registrarMovimiento(dto);
        return ResponseEntity.ok(creado);
    }
}
