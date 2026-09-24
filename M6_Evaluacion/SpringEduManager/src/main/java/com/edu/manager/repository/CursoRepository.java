package com.edu.manager.repository;

import com.edu.manager.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Al heredar de JpaRepository, Spring genera automáticamente todos los métodos CRUD (save, findAll, delete, etc.)
}
