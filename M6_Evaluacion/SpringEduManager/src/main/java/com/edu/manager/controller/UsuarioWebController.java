package com.edu.manager.controller;

import com.edu.manager.model.Usuario;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Clase que egistra nuevos estudiantes o administradores en el sistema, y listar a todos los
 * usuarios que actualmente tienen acceso a la plataforma.
 */
@Controller
public class UsuarioWebController {
    private final UsuarioRepository usuarioRepository;
    /**
     * Inyección del repositorio de usuarios por constructor.
     * @param usuarioRepository objeto que sirve para operar con MySQL.
     */
    public UsuarioWebController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /**
     * Ruta para ver la pantalla de gestión de usuarios: http://localhost:8080/usuarios
     * Trae todos los usuarios de MySQL y los inyectamos en la variable "listaUsuarios".
     * @param model objeto caja de datos MySQL.
     * @return nombre del archivo HTML que crearemos más adelante ("usuarios.html")
     */
    @GetMapping("/usuarios")
    public String mostrarPantallaUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "usuarios";
    }
    /**
     * Ruta para procesar el formulario de registro de un nuevo estudiante o admin.
     * Hibernate realiza el INSERT INTO automático en la tabla usuarios de MySQL.
     * @param usuario
     * @return redirecciona y refresca la pantalla para mostrar al usuario recién creado.
     */
    @PostMapping("/usuarios/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
}
