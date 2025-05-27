package com.vetcontrol.service;

import com.vetcontrol.dto.MovimientoInventarioDTO;

import java.util.List;

public interface MovimientoInventarioService {

    MovimientoInventarioDTO registrarMovimiento(MovimientoInventarioDTO dto);

    List<MovimientoInventarioDTO> listarTodos();

    List<MovimientoInventarioDTO> listarPorProducto(Long productoId);
}
