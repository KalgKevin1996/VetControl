package com.vetcontrol.service;

import com.vetcontrol.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarTodos();
    void guardar(UsuarioDTO usuarioDTO);
    UsuarioDTO buscarPorId(Long id);
    void eliminar(Long id);
}
