package com.edu.manager.component;

import com.edu.manager.model.Actividad;
import com.edu.manager.model.Curso;
import com.edu.manager.model.Usuario;
import com.edu.manager.repository.ActividadRepository;
import com.edu.manager.repository.CursoRepository;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/**
 * Clase para crear data de prueba, si la base de datos esta vacía.
 */
@Component
public class DataLoader implements CommandLineRunner{
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final ActividadRepository actividadRepository;
    // Inyección por constructor de los tres repositorios necesarios
    public DataLoader(UsuarioRepository usuarioRepository, CursoRepository cursoRepository, ActividadRepository actividadRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.actividadRepository = actividadRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        // Al usar ddl-auto=update, verificamos primero que las tablas estén vacías para no duplicar datos en cada reinicio
        if (usuarioRepository.count() == 0) {
            System.out.println("====== INICIANDO INYECCIÓN DE DATOS DE PRUEBA ======");
            Usuario admin = new Usuario("Administrador", "admin@bootcamp.com", "admin123", "ROLE_ADMIN", 35);
            Usuario estudiante = new Usuario("Estudiante Juan", "juan@bootcamp.com", "user123", "ROLE_USER", 24);
            usuarioRepository.save(admin);
            usuarioRepository.save(estudiante);
            Curso curso1 = new Curso("Desarrollo de Aplicaciones JEE con Spring Framework", "Módulo intensivo de Spring Boot, JPA y Seguridad.");
            Curso curso2 = new Curso("Bases de Datos Relacionales y NoSQL", "Modelado, optimización y consultas avanzadas.");
            Curso curso3 = new Curso("Fundamentos de Java y Programación Orientada a Objetos", "Sintaxis base, colecciones y manejo de excepciones.");
            cursoRepository.save(curso1);
            cursoRepository.save(curso2);
            cursoRepository.save(curso3);
            Actividad practica1 = new Actividad(estudiante, curso1, "Práctica 1: Configuración de Entidades JPA", 6.5);
            Actividad practica2 = new Actividad(estudiante, curso1, "Evaluación Módulo 6: SpringEduManager Base", 7.0);
            Actividad practica3 = new Actividad(estudiante, curso2, "Práctica 2: Consultas Complejas en MySQL", 5.8);
            actividadRepository.save(practica1);
            actividadRepository.save(practica2);
            actividadRepository.save(practica3);
            System.out.println("====== INYECCIÓN DE DATOS COMPLETADA CON ÉXITO ======");
        } else {
            System.out.println("====== LA BASE DE DATOS YA TIENE DATOS, SE OMITE LA INYECCIÓN ======");
        }
    }
}
