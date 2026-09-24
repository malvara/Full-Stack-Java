package com.edu.manager.controller;

import com.edu.manager.model.Curso;
import com.edu.manager.repository.CursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoWebController {
    private final CursoRepository cursoRepository;
    /**
     * Inyección del repositorio de cursos por constructor
     * @param cursoRepository capa de persistencia de los cursos en la aplicación.
     */
    public CursoWebController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    /**
     * Ruta para ver la pantalla: http://localhost:8080/cursos
     * Guarda lista de cursos de MysQL en objeto model model.
     * @param model objeto caja de datos MySQL.
     * @return nombre del archivo HTML que crearemos en el próximo paso ("cursos.html")
     */
    @GetMapping("/cursos")
    public String mostrarPantallaCursos(Model model) {
        model.addAttribute("listaCursos", cursoRepository.findAll());
        return "cursos";
    }
    /**
     * Ruta para recibir los datos del formulario web y guardarlos en MySQL.
     * @param curso objeto Curso.
     * @return redirecciona y refresca la pantalla principal para que aparezca el curso recién guardado.
     */
    @PostMapping("/cursos/guardar")
    public String guardarNuevoCurso(@ModelAttribute Curso curso) {
        cursoRepository.save(curso);
        return "redirect:/cursos";
    }
}
