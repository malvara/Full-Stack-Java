package com.edu.manager.repository;

import com.edu.manager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // permite que Spring Security busque al alumno por su correo al iniciar sesión
    Optional<Usuario> findByUsername(String username);
}
