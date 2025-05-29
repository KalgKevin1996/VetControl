package com.vetcontrol.controller.web;

import com.vetcontrol.dto.UsuarioDTO;
import com.vetcontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO, Model model) {
        if (!usuarioDTO.getPassword().equals(usuarioDTO.getConfirmPassword())) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "usuarios/formulario";
        }

        usuarioService.guardar(usuarioDTO);
        return "redirect:/usuarios";
    }


    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuarioDTO);
        return "usuarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}
