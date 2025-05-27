package com.vetcontrol.service.impl;

import com.vetcontrol.dto.MovimientoInventarioDTO;
import com.vetcontrol.entity.MovimientoInventario;
import com.vetcontrol.entity.Producto;
import com.vetcontrol.entity.TipoMovimiento;
import com.vetcontrol.repository.MovimientoInventarioRepository;
import com.vetcontrol.repository.ProductoRepository;
import com.vetcontrol.service.MovimientoInventarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepo;
    private final ProductoRepository productoRepo;

    @Override
    public MovimientoInventarioDTO registrarMovimiento(MovimientoInventarioDTO dto) {
        Producto producto = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        int nuevaCantidad = dto.getTipoMovimiento() == TipoMovimiento.ENTRADA
                ? producto.getStock() + dto.getCantidad()
                : producto.getStock() - dto.getCantidad();

        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("Stock insuficiente para realizar la salida.");
        }

        producto.setStock(nuevaCantidad);
        productoRepo.save(producto);

        MovimientoInventario movimiento = MovimientoInventario.builder()
                .producto(producto)
                .tipoMovimiento(dto.getTipoMovimiento())
                .cantidad(dto.getCantidad())
                .fecha(LocalDateTime.now())
                .descripcion(dto.getDescripcion())
                .build();

        movimiento = movimientoRepo.save(movimiento);

        return convertirADTO(movimiento);
    }

    @Override
    public List<MovimientoInventarioDTO> listarTodos() {
        return movimientoRepo.findAllByOrderByFechaDesc().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoInventarioDTO> listarPorProducto(Long productoId) {
        return movimientoRepo.findByProductoIdOrderByFechaDesc(productoId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private MovimientoInventarioDTO convertirADTO(MovimientoInventario m) {
        return MovimientoInventarioDTO.builder()
                .id(m.getId())
                .productoId(m.getProducto().getId())
                .tipoMovimiento(m.getTipoMovimiento())
                .cantidad(m.getCantidad())
                .descripcion(m.getDescripcion())
                .fecha(m.getFecha()) // ✅ aquí se añade
                .build();
    }

}
