package com.vetcontrol.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperadorController {

    @GetMapping("/operador/dashboard")
    public String mostrarDashboardOperador() {
        return "operador/dashboard";
    }
}
