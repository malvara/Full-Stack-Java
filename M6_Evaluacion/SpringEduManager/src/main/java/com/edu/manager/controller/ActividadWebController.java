package com.edu.manager.controller;

import com.edu.manager.model.Actividad;
import com.edu.manager.repository.ActividadRepository;
import com.edu.manager.repository.CursoRepository;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ActividadWebController {
    private final ActividadRepository actividadRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    /**
     * Inyección del repositorio de actividades por constructor.
     * @param actividadRepository objeto que sirve para operar con MySQL.
     */
    //
    public ActividadWebController(ActividadRepository actividadRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.actividadRepository = actividadRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    /**Ruta para ver la pantalla: http://localhost:8080/actividades.
     * Permite pasar un parámetro opcional para simular el rol de estudiante, ej: /actividades?usuarioId=2
     * @param usuarioId identificador usuario.
     * @param model objeto caja de datos MySQL.
     * @return nombre del archivo HTML que crearemos más adelante ("actividades.html").
     */
    @GetMapping("/actividades")
    public String mostrarPantallaActividades(
            @RequestParam(value = "usuarioId", required = false) Long usuarioId,
            @RequestParam(value = "cursoId", required = false) Long cursoId,
            jakarta.servlet.http.HttpSession session, // <-- Asegúrate de tener este parámetro inyectado
            Model model) {
        // Recuperamos al usuario autenticado de la sesión
        com.edu.manager.model.Usuario usuarioLogueado = (com.edu.manager.model.Usuario) session.getAttribute("usuariosession");
        List<Actividad> listaFiltrada;
        // REGLA DE PRIVATIZACIÓN: Si el usuario es un estudiante (USER), se le prohíbe ver todo
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("USER")) {
            if (cursoId != null) {
                // Si hace clic en un curso específico desde sus tarjetas
                listaFiltrada = actividadRepository.findByUsuarioId(usuarioLogueado.getId()).stream()
                        .filter(a -> a.getCurso().getId().equals(cursoId))
                        .collect(java.util.stream.Collectors.toList());
                model.addAttribute("modoVista", "Mis Calificaciones por Curso");
            } else {
                // Si ingresa directo desde la barra de navegación superior
                listaFiltrada = actividadRepository.findByUsuarioId(usuarioLogueado.getId());
                model.addAttribute("modoVista", "Mi Historial Académico Personal");
            }
        } else {
            // FLUJO DEL ADMIN (Mantiene el CRUD Global)
            if (usuarioId != null) {
                if (cursoId != null) {
                    listaFiltrada = actividadRepository.findByUsuarioId(usuarioId).stream()
                            .filter(a -> a.getCurso().getId().equals(cursoId))
                            .collect(java.util.stream.Collectors.toList());
                } else {
                    listaFiltrada = actividadRepository.findByUsuarioId(usuarioId);
                }
                model.addAttribute("modoVista", "Historial de Alumno Seleccionado");
            } else {
                listaFiltrada = actividadRepository.findAll();
                model.addAttribute("modoVista", "Coordinación Académica (Global)");
            }
        }
        model.addAttribute("listaActividades", listaFiltrada);
        model.addAttribute("todosLosCursos", cursoRepository.findAll());
        model.addAttribute("todosLosUsuarios", usuarioRepository.findAll());
        if (!model.containsAttribute("actividadEdit")) {
            model.addAttribute("actividadEdit", new Actividad());
        }
        return "actividades";
    }
    // CORRECCIÓN: Agregamos HttpSession session a los parámetros de entrada y a la llamada de la línea 85
    @GetMapping("/actividades/editar/{id}")
    public String prepararEdicionActividad(
            @PathVariable("id") Long id,
            @RequestParam(value = "usuarioId", required = false) Long usuarioId,
            @RequestParam(value = "cursoId", required = false) Long cursoId,
            jakarta.servlet.http.HttpSession session, // <-- Parámetro inyectado aquí
            Model model) {
        Actividad act = actividadRepository.findById(id).orElse(new Actividad());
        model.addAttribute("actividadEdit", act);
        // CORRECCIÓN: Le pasamos la session a la llamada interna para que coincida con la firma
        return mostrarPantallaActividades(usuarioId, cursoId, session, model);
    }
    @PostMapping("/actividades/guardar")
    public String guardarNuevaActividad(@ModelAttribute Actividad deActividad) {
        actividadRepository.save(deActividad);
        return "redirect:/actividades";
    }

    @GetMapping("/actividades/eliminar/{id}")
    public String eliminarActividad(@PathVariable("id") Long id) {
        actividadRepository.deleteById(id);
        return "redirect:/actividades";
    }
}
