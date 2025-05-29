package com.vetcontrol.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetcontrol.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) throws JsonProcessingException {
        model.addAttribute("totalMovimientos", reporteService.contarMovimientos());

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labels", mapper.writeValueAsString(reporteService.obtenerLabelsTiposMovimiento()));
        model.addAttribute("values", mapper.writeValueAsString(reporteService.obtenerValoresTiposMovimiento()));
        model.addAttribute("totalProductos", reporteService.contarProductos());
        model.addAttribute("activos", reporteService.contarProductosActivos());
        model.addAttribute("inactivos", reporteService.contarProductosInactivos());
        List<Object[]> topIngresos = reporteService.topProductosIngresos();
        model.addAttribute("topIngresosNombres", mapper.writeValueAsString(topIngresos.stream().map(d -> d[0]).toList()));
        model.addAttribute("topIngresosValores", mapper.writeValueAsString(topIngresos.stream().map(d -> d[1]).toList()));
        List<Object[]> topSalidas = reporteService.topProductosSalidas();
        model.addAttribute("topSalidasNombres", mapper.writeValueAsString(topSalidas.stream().map(d -> d[0]).toList()));
        model.addAttribute("topSalidasValores", mapper.writeValueAsString(topSalidas.stream().map(d -> d[1]).toList()));





        return "reportes/dashboard";
    }
}
