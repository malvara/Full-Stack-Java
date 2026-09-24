package com.edu.manager.controller;

import com.edu.manager.model.Actividad;
import com.edu.manager.repository.ActividadRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actividades") // URL base para las notas y prácticas
public class ActividadRestController {
    private final ActividadRepository actividadRepository;
    public ActividadRestController(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }
    /**
     * Endpoint General
     * @return todas las notas de todos los alumnos (Útil para el rol ADMIN).
     */
    @GetMapping
    public List<Actividad> obtenerTodasLasActividades() {
        return actividadRepository.findAll();
    }
    /**
     * Endpoint Filtrado (ejmplo: http://localhost:8080/api/actividades/estudiante/2)
     * @param id identificador del usuario estudiante.
     * @return notas de un alumno específico usando su ID en la URL
     */
    @GetMapping("/estudiante/{id}")
    public List<Actividad> obtenerActividadesPorEstudiante(@PathVariable("id") Long id) {
        return actividadRepository.findByUsuarioId(id); // Ejecuta el Query Method automático de Spring
    }
}
