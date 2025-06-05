package com.vetcontrol.controller.web;

import com.vetcontrol.dto.MovimientoInventarioDTO;
import com.vetcontrol.entity.Producto;
import com.vetcontrol.entity.TipoMovimiento;
import com.vetcontrol.repository.ProductoRepository;
import com.vetcontrol.service.MovimientoInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;
    private final ProductoRepository productoRepository;

    @GetMapping
    public String listarMovimientos(Model model) {
        model.addAttribute("movimientos", movimientoService.listarTodos());
        return "movimientos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("movimientoDTO", new MovimientoInventarioDTO());
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("tipos", TipoMovimiento.values());
        return "movimientos/formulario";
    }

    @PostMapping("/guardar")
    public String registrar(@Valid @ModelAttribute("movimientoDTO") MovimientoInventarioDTO dto,
                            BindingResult result,
                            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            model.addAttribute("tipos", TipoMovimiento.values());
            return "movimientos/formulario";
        }

        try {
            movimientoService.registrarMovimiento(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("productos", productoRepository.findAll());
            model.addAttribute("tipos", TipoMovimiento.values());
            model.addAttribute("error", e.getMessage());
            return "movimientos/formulario";
        }

        return "redirect:/movimientos";
    }
}
