package com.vetcontrol.repository;

import com.vetcontrol.entity.MovimientoInventario;
import com.vetcontrol.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    long countByTipoMovimiento(TipoMovimiento tipoMovimiento);

    List<MovimientoInventario> findByProductoId(Long productoId);

    List<MovimientoInventario> findByProductoIdOrderByFechaDesc(Long productoId);

    List<MovimientoInventario> findAllByOrderByFechaDesc();

}
