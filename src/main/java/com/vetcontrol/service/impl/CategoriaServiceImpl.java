package com.vetcontrol.service.impl;

import com.vetcontrol.dto.CategoriaDTO;
import com.vetcontrol.entity.Categoria;
import com.vetcontrol.repository.CategoriaRepository;
import com.vetcontrol.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
        return convertirADTO(categoria);
    }

    @Override
    public CategoriaDTO guardar(CategoriaDTO dto) {
        Categoria nueva = new Categoria();
        nueva.setNombre(dto.getNombre());
        nueva.setDescripcion(dto.getDescripcion());
        Categoria guardada = categoriaRepository.save(nueva);
        return convertirADTO(guardada);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO dto) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        return convertirADTO(categoriaRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO convertirADTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
