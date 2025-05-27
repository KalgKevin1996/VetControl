package com.vetcontrol.service;

import com.vetcontrol.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDTO> listarTodas();
    CategoriaDTO buscarPorId(Long id);
    CategoriaDTO guardar(CategoriaDTO categoriaDTO);
    CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO);
    void eliminar(Long id);
}
