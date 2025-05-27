package com.vetcontrol.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')") // Reforzamos seguridad a nivel de método
    public String dashboardAdmin() {
        return "admin/dashboard"; // apunta a templates/admin/dashboard.html
    }
}
