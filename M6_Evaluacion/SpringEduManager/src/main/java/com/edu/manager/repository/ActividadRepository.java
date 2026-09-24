package com.edu.manager.repository;

import com.edu.manager.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long>{
    // Este método nos servirá para que el rol USER consulte solo sus propias notas de forma sencilla
    List<Actividad> findByUsuarioId(Long usuarioId);
}
