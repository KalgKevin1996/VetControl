package com.vetcontrol.service.impl;

import com.vetcontrol.dto.UsuarioDTO;
import com.vetcontrol.entity.Usuario;
import com.vetcontrol.repository.UsuarioRepository;
import com.vetcontrol.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // 🔐 Inyecta el encoder


    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void guardar(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

        // Encriptar la contraseña solo si es nueva o fue modificada
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        } else {
            // Si es edición y no se cambia contraseña, mantener la actual
            if (usuarioDTO.getId() != null) {
                usuario.setPassword(usuarioRepository.findById(usuarioDTO.getId())
                        .map(Usuario::getPassword)
                        .orElseThrow(() -> new RuntimeException("No se pudo obtener la contraseña actual")));
            }
        }

        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }



}
