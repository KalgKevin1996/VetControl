package com.vetcontrol.repository;

import com.vetcontrol.entity.Producto;
import com.vetcontrol.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    long countByActivoTrue();
    long countByActivoFalse();

    List<Producto> findByActivoTrue();
    boolean existsByNombre(String nombre);



}
