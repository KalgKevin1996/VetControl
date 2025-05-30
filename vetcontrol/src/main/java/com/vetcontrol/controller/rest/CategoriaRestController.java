package com.vetcontrol.controller.rest;

import com.vetcontrol.dto.CategoriaDTO;
import com.vetcontrol.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Operaciones relacionadas con categorías de productos")
public class CategoriaRestController {

    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas las categorías")
    public List<CategoriaDTO> listarTodas() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID")
    public CategoriaDTO obtenerPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva categoría")
    public CategoriaDTO crear(@RequestBody CategoriaDTO dto) {
        return categoriaService.guardar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría")
    public CategoriaDTO actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        dto.setId(id);
        return categoriaService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
