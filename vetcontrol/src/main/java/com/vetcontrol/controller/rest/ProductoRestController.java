package com.vetcontrol.controller.rest;

import com.vetcontrol.dto.ProductoDTO;
import com.vetcontrol.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductoRestController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<ProductoDTO> listarTodos() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ProductoDTO obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.guardar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        dto.setId(id);
        return productoService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
