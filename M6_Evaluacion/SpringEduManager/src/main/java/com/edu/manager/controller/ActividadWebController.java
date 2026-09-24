package com.edu.manager.controller;

import com.edu.manager.model.Actividad;
import com.edu.manager.repository.ActividadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ActividadWebController {
    private final ActividadRepository actividadRepository;
    /**
     * Inyección del repositorio de actividades por constructor.
     * @param actividadRepository objeto que sirve para operar con MySQL.
     */
    //
    public ActividadWebController(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }
    /**Ruta para ver la pantalla: http://localhost:8080/actividades.
     * Permite pasar un parámetro opcional para simular el rol de estudiante, ej: /actividades?usuarioId=2
     * @param usuarioId identificador usuario.
     * @param model objeto caja de datos MySQL.
     * @return nombre del archivo HTML que crearemos más adelante ("actividades.html").
     */
    @GetMapping("/actividades")
    public String mostrarPantallaActividades(@RequestParam(value = "usuarioId", required = false) Long usuarioId, Model model) {
        List<Actividad> listaFiltrada;
        if (usuarioId != null) {
            listaFiltrada = actividadRepository.findByUsuarioId(usuarioId);
            model.addAttribute("modoVista", "Estudiante (Filtrado)");
        } else {
            listaFiltrada = actividadRepository.findAll();
            model.addAttribute("modoVista", "Coordinación Académica (Global)");
        }
        model.addAttribute("listaActividades", listaFiltrada);
        return "actividades";
    }
}
