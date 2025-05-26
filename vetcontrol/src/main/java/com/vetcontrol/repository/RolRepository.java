package com.vetcontrol.repository;

import com.vetcontrol.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository {
    Optional<Rol> findByNombre(String nombre);
}
