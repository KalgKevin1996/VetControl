package com.vetcontrol.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/403")
    public String accesoDenegado() {
        return "error/403";
    }
}
