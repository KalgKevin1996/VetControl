package com.vetcontrol.repository;

import com.vetcontrol.entity.MovimientoInventario;
import com.vetcontrol.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    long countByTipoMovimiento(TipoMovimiento tipoMovimiento);

    List<MovimientoInventario> findByProductoId(Long productoId);

    List<MovimientoInventario> findByProductoIdOrderByFechaDesc(Long productoId);

    List<MovimientoInventario> findAllByOrderByFechaDesc();
    @Query("SELECT m.producto.nombre, COUNT(m) " +
            "FROM MovimientoInventario m " +
            "WHERE m.tipoMovimiento = :tipo " +
            "GROUP BY m.producto.nombre " +
            "ORDER BY COUNT(m) DESC")
    List<Object[]> top5ProductosPorTipoMovimiento(@Param("tipo") TipoMovimiento tipo);

}
