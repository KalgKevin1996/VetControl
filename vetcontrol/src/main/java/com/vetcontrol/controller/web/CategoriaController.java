package com.vetcontrol.controller.web;

import com.vetcontrol.dto.CategoriaDTO;
import com.vetcontrol.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@Valid @ModelAttribute("categoriaDTO") CategoriaDTO categoriaDTO,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categorias/formulario";
        }
        categoriaService.guardar(categoriaDTO);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        CategoriaDTO dto = categoriaService.buscarPorId(id);
        model.addAttribute("categoriaDTO", dto);
        return "categorias/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCategoria(@PathVariable Long id,
                                      @Valid @ModelAttribute("categoriaDTO") CategoriaDTO categoriaDTO,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categorias/formulario";
        }
        categoriaService.actualizar(id, categoriaDTO);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}
