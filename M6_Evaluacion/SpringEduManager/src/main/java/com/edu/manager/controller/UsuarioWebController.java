package com.edu.manager.controller;

import com.edu.manager.model.Usuario;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que egistra nuevos estudiantes o administradores en el sistema, y listar a todos los
 * usuarios que actualmente tienen acceso a la plataforma.
 */
@Controller
public class UsuarioWebController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * Inyección del repositorio de usuarios por constructor.
     * @param usuarioRepository objeto que sirve para operar con MySQL.
     */
    public UsuarioWebController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
        // CORRECCIÓN BLINDADA: Evita el error 500 asegurando el objeto para el formulario de usuarios
        if (!model.containsAttribute("usuarioEdit")) {
            model.addAttribute("usuarioEdit", new Usuario());
        }
        return "usuarios";
    }
    @GetMapping("/usuarios/editar/{id}")
    public String prepararEdicionUsuario(@PathVariable("id") Long id, Model model) {
        Usuario user = usuarioRepository.findById(id).orElse(new Usuario());
        model.addAttribute("usuarioEdit", user);
        return mostrarPantallaUsuarios(model);
    }
    /**
     * Ruta para procesar el formulario de registro de un nuevo estudiante o admin.
     * Hibernate realiza el INSERT INTO automático en la tabla usuarios de MySQL.
     * @param usuario
     * @return redirecciona y refresca la pantalla para mostrar al usuario recién creado.
     */
    @PostMapping("/usuarios/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario) {
        // CORRECCIÓN: Si es un usuario nuevo o se modificó la clave, forzamos la encriptación BCrypt limpia
        if (usuario.getId() == null || (usuario.getPassword() != null && !usuario.getPassword().isEmpty())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            // Si estamos editando y no se digitó clave nueva, mantenemos la contraseña actual de la BD
            Usuario usuarioActual = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (usuarioActual != null) {
                usuario.setPassword(usuarioActual.getPassword());
            }
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
