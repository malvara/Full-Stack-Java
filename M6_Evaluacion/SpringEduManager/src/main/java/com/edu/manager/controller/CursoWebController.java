package com.edu.manager.controller;

import com.edu.manager.model.Curso;
import com.edu.manager.model.Usuario;
import com.edu.manager.model.Actividad;
import com.edu.manager.repository.ActividadRepository;
import com.edu.manager.repository.CursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CursoWebController {
    private final CursoRepository cursoRepository;
    private final ActividadRepository actividadRepository;
    /**
     * Inyección del repositorio de cursos por constructor
     * @param cursoRepository capa de persistencia de los cursos en la aplicación.
     */
    public CursoWebController(CursoRepository cursoRepository, ActividadRepository actividadRepository) {
        this.cursoRepository = cursoRepository;
        this.actividadRepository = actividadRepository;
    }
    /**
     * Ruta para ver la pantalla: http://localhost:8080/cursos
     * Guarda lista de cursos de MysQL en objeto model model.
     * @param model objeto caja de datos MySQL.
     * @return nombre del archivo HTML que crearemos en el próximo paso ("cursos.html")
     */
    @GetMapping("/cursos")
    public String mostrarPantallaCursos(HttpSession session, Model model) {
        // 1. Rescatamos el usuario que inició sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuariosession");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("USER")) {
            // 2. Hacemos la consulta simple en la tabla actividades filtrando por el ID del usuario
            List<Actividad> actividadesDeJuan = actividadRepository.findByUsuarioId(usuarioLogueado.getId());
            // 3. Rescatamos los cursos asociados a esas actividades
            List<Curso> cursosAsociados = actividadesDeJuan.stream()
                    .map(Actividad::getCurso)
                    .distinct()
                    .collect(Collectors.toList());
            // 4. Mostramos la lista de cursos asociados en la pantalla
            model.addAttribute("listaActividadesPersonales", cursosAsociados);
        }
        model.addAttribute("listaCursos", cursoRepository.findAll());
        model.addAttribute("cursoEdit", new Curso());
        return "cursos";
    }
    // NUEVO MÉTODO CRUD (Update - Buscar): Captura el objeto y recarga la pantalla rellenando los campos
    @GetMapping("/cursos/editar/{id}")
    public String prepararEdicionCurso(@PathVariable("id") Long id, HttpSession session, Model model) {
        Curso cursoAEditar = cursoRepository.findById(id).orElse(new Curso());
        model.addAttribute("cursoEdit", cursoAEditar);
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuariosession");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("USER")) {
            List<Curso> cursosParticipa = actividadRepository.findByUsuarioId(usuarioLogueado.getId()).stream()
                    .map(act -> act.getCurso())
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute("listaActividadesPersonales", cursosParticipa);
        }
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
    // OPERACIÓN CRUD (Eliminar Curso): Borra el registro de la base de datos
    @GetMapping("/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long id) {
        cursoRepository.deleteById(id);
        return "redirect:/cursos";
    }
}
