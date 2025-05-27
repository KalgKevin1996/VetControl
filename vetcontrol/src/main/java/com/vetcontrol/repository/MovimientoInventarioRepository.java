package com.vetcontrol.repository;

import com.vetcontrol.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    List<MovimientoInventario> findByProductoId(Long productoId);

    List<MovimientoInventario> findByProductoIdOrderByFechaDesc(Long productoId);

    List<MovimientoInventario> findAllByOrderByFechaDesc();
}
