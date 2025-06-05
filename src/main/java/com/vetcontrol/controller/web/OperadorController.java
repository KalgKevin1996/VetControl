package com.vetcontrol.controller.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operador")
public class OperadorController {

    @PreAuthorize("hasRole('OPERADOR')")
    @GetMapping("/dashboard")
    public String mostrarDashboardOperador() {
        return "operador/dashboard";
    }
}
