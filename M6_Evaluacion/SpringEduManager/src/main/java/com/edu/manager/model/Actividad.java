package com.edu.manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "actividades")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actividad_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    @Column(name = "actividad_practica", nullable = false)
    private String practica;
    @Column(name = "actividad_evaluacion")
    private Double evaluacion;
    /**
     * Constructor vacío obligatorio para JPA
     */
    public Actividad() {
    }
    /**
     * Constructor con parámetros
     * @param usuario identificador del usuario.
     * @param curso identificasor del curso.
     * @param practica descripción de la práctica.
     * @param evaluacion califivación de la práctica (Nota del 1.0 al 7.0).
     */
    public Actividad(Usuario usuario, Curso curso, String practica, Double evaluacion) {
        this.usuario = usuario;
        this.curso = curso;
        this.practica = practica;
        this.evaluacion = evaluacion;
    }
    /**
     * Método para obtener el ID de la actividad.
     * @return identificador de la actividad.
     */
    public Long getId() {
        return id;
    }
    /**
     * Método para modificar el ID de la actividad.
     * @param id identificador de la actividad.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Método para obtener el ID del usuario de la actividad.
     * @return identificador del usrario.
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Método para modificar el ID del usuario del de la actividad.
     * @param usuario nuevo identificador de usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Método para obtener el ID del curso de la actividad.
     * @return identificador de curso.
     */
    public Curso getCurso() {
        return curso;
    }
    /**
     * Método para modificar el ID del curso de la actividad.
     * @param curso nuevo identificador de curso.
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    /**
     * Método para obtener el ID de la práctica de la actividad.
     * @return descripción de la práctica.
     */
    public String getPractica() {
        return practica;
    }
    /**
     * Método para modificar el ID de la práctica de la actividad.
     * @param practica nuevo identificador de la práctica.
     */
    public void setPractica(String practica) {
        this.practica = practica;
    }
    /**
     * Método para obtener la calificación de la actividad.
     * @return calificación de la actividad.
     */
    public Double getEvaluacion() {
        return evaluacion;
    }
    /**
     * Método para modificar el calificación del de la actividad.
     * @param evaluacion nueva calificación de la actividad.
     */
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }
}
