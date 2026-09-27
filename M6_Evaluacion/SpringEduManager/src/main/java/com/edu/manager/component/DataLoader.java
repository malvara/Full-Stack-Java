package com.edu.manager.component;

import com.edu.manager.model.Actividad;
import com.edu.manager.model.Curso;
import com.edu.manager.model.Usuario;
import com.edu.manager.repository.ActividadRepository;
import com.edu.manager.repository.CursoRepository;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * Clase para crear data de prueba, si la base de datos esta vacía.
 */
@Component
public class DataLoader implements CommandLineRunner{
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final ActividadRepository actividadRepository;
    private final PasswordEncoder passwordEncoder;
    // Inyección por constructor de los tres repositorios necesarios
    public DataLoader(UsuarioRepository usuarioRepository,
                      CursoRepository cursoRepository,
                      ActividadRepository actividadRepository,
                      PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.actividadRepository = actividadRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            System.out.println("====== INICIANDO INYECCIÓN DE DATOS DE PRUEBA ENCRIPTADOS ======");

            // 1. Creamos y guardamos los usuarios (MySQL les asignará IDs autoincrementales automáticamente)
            Usuario admin = new Usuario("Administrador", "admin@bootcamp.com", passwordEncoder.encode("admin123"), "ADMIN", 35);
            Usuario estudiante = new Usuario("Estudiante Juan", "juan@bootcamp.com", passwordEncoder.encode("user123"), "USER", 24);

            admin = usuarioRepository.save(admin);
            estudiante = usuarioRepository.save(estudiante);

            // 2. Creamos y guardamos los cursos
            Curso curso1 = new Curso("Desarrollo de Aplicaciones JEE con Spring Framework", "Módulo intensivo de Spring Boot, JPA y Seguridad.");
            Curso curso2 = new Curso("Bases de Datos Relacionales y NoSQL", "Modelado, optimización y consultas avanzadas.");
            Curso curso3 = new Curso("Fundamentos de Java y Programación Orientada a Objetos", "Sintaxis base, colecciones y manejo de excepciones.");
            Curso curso4 = new Curso("Estadística", "Identificación de patrones.");

            curso1 = cursoRepository.save(curso1);
            curso2 = cursoRepository.save(curso2);
            curso3 = cursoRepository.save(curso3);
            curso4 = cursoRepository.save(curso4);

            // 3. Creamos las notas enlazándolas al objeto 'estudiante' real.
            // ¡Aquí ocurre la magia! Hibernate toma el ID autoincremental real de Juan (sea 2, 5 o 20) de forma automática.
            Actividad practica1 = new Actividad(estudiante, curso1, "Práctica 1: Configuración de Entidades JPA", 6.5);
            Actividad practica2 = new Actividad(estudiante, curso1, "Evaluación Módulo 6: SpringEduManager Base", 7.0);
            Actividad practica3 = new Actividad(estudiante, curso2, "Práctica 2: Consultas Complejas en MySQL", 5.8);
            Actividad practica4 = new Actividad(estudiante, curso2, "aplicar sentencia select", 5.2);

            actividadRepository.save(practica1);
            actividadRepository.save(practica2);
            actividadRepository.save(practica3);
            actividadRepository.save(practica4);

            System.out.println("====== INYECCIÓN DE DATOS COMPLETADA CON ÉXITO ======");
        } else {
            System.out.println("====== LA BASE DE DATOS YA TIENE DATOS, SE OMITE LA INYECCIÓN ======");
        }
        // ====================================================================
        // BLOQUE DE DIAGNÓSTICO: Imprime los IDs reales para ver el desfase
        // ====================================================================
        System.out.println("🔍 --- REVISIÓN DE SEGURIDAD DE IDENTIFICADORES (IDs) ---");

        usuarioRepository.findAll().forEach(u ->
                System.out.println("👤 USUARIO EN BD -> Nombre: [" + u.getNombre() + "] | Cuenta: [" + u.getUsername() + "] | ID Real en MySQL: " + u.getId())
        );

        actividadRepository.findAll().forEach(act ->
                System.out.println("📝 PRÁCTICA EN BD -> Tarea: [" + act.getPractica() + "] | Enlazada al Usuario ID: " + (act.getUsuario() != null ? act.getUsuario().getId() : "NULO"))
        );
        System.out.println("🔍 ----------------------------------------------------");
    }
}
