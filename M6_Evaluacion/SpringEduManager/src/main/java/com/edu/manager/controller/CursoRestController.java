package com.edu.manager.controller;

import com.edu.manager.model.Curso;
import com.edu.manager.repository.CursoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que responderá datos JSON directamente al navegador.
 */
@RestController
@RequestMapping("/api/cursos") // Define la URL base para consumir este servicio
public class CursoRestController {
    private final CursoRepository cursoRepository;
    /**
     * Inyección por constructor del repositorio de cursos
     * @param cursoRepository capa de persistencia de los cursos en la aplicación.
     */
    public CursoRestController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    /**
     * Endpoint de lectura
     * Método que se ejecuta cuando se ingresa a http://localhost:8080/api/cursos.
     * @return lista que Hibernate convertirá en JSON automáticamente.
     */
    @GetMapping
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }
}
