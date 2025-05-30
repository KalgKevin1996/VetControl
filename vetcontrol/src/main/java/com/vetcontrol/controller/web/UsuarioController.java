package com.vetcontrol.controller.web;

import com.vetcontrol.dto.UsuarioDTO;
import com.vetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO,
                                 BindingResult result,
                                 Model model) {
        // Validar que las contraseñas coincidan
        if (!usuarioDTO.getPassword().equals(usuarioDTO.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Las contraseñas no coinciden");
        }

        if (result.hasErrors()) {
            return "usuarios/formulario"; // vuelve al formulario si hay errores
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
