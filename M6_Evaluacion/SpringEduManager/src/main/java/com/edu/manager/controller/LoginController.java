package com.edu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    // Registra la ruta de entrada para tu pantalla integrada de bienvenida
    @GetMapping("/login")
    public String mostrarPantallaBienvenida() {
        return "login"; // Apunta al archivo src/main/resources/templates/login.html
    }
}
