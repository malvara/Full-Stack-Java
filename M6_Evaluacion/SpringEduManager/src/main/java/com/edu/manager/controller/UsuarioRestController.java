package com.edu.manager.controller;

import com.edu.manager.model.Usuario;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * endpoint que permite consultar el listado completo de los usuarios registrados.
 */
@RestController
@RequestMapping("/api/usuarios") // URL base para los usuarios
public class UsuarioRestController {
    private final UsuarioRepository usuarioRepository;
    public UsuarioRestController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /**
     * Método que se ejecuta cuando se ingresa a http://localhost:8080/api/usuarios
     * @return listar todos los usuarios registrados (ADMIN y USER).
     */
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
