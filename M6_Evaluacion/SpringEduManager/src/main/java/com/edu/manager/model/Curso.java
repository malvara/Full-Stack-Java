package com.edu.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private String descripcion;
    /**
     * Constructor vacío obligatorio para JPA
     */
    public Curso() {
    }
    /**
     * Constructor con parámetros.
     * @param nombre nombre del curso.
     * @param descripcion descripción del curso.
     */
    public Curso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    /**
     * Método que obtiene el ID del curso.
     * @return identificadoe del cueso.
     */
    public Long getId() {
        return id;
    }
    /**
     * Método que modifica el ID del curso.
     * @param id nuevo identificador del curso.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Método que obtiene el nombre del curso.
     * @return nombre del cueso.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método que modifica el nombre del curso.
     * @param nombre nombre del curso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método que obtiene la descripción del curso.
     * @return nueva descripción del cueso.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Método que modifica la descripción del curso.
     * @param descripcion descripción del curso.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
