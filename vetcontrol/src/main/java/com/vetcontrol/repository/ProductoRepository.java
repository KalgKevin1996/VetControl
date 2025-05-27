package com.vetcontrol.repository;

import com.vetcontrol.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByActivoTrue();
    boolean existsByNombre(String nombre);
}
