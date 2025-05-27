package com.vetcontrol.service.impl;

import com.vetcontrol.dto.ProductoDTO;
import com.vetcontrol.entity.Categoria;
import com.vetcontrol.entity.Producto;
import com.vetcontrol.repository.CategoriaRepository;
import com.vetcontrol.repository.ProductoRepository;
import com.vetcontrol.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return convertirADTO(producto);
    }

    @Override
    public ProductoDTO guardar(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .imagen(dto.getImagen())
                .activo(dto.isActivo())
                .categoria(categoria)
                .build();

        return convertirADTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());
        existente.setImagen(dto.getImagen());
        existente.setActivo(dto.isActivo());
        existente.setCategoria(categoria);

        return convertirADTO(productoRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .imagen(producto.getImagen())
                .activo(producto.isActivo())
                .categoriaId(producto.getCategoria().getId())
                .build();
    }
}
