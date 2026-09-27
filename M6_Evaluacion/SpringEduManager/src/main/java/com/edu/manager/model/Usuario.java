package com.edu.manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    @Column(name = "usuario_nombre", nullable = false)
    private String nombre;
    @Column(name = "usuario_email", unique = true, nullable = false)
    private String username;
    @Column(name = "usuario_password", nullable = false, length = 100)
    private String password;
    @Column(name = "usuario_rol", nullable = false, length = 100)
    private String rol; // Aquí guardaremos "ROLE_ADMIN" o "ROLE_USER"
    @Column(name = "usuario_edad")
    private Integer edad;
    /**
     * Constructor vacío obligatorio para JPA
     */
    public Usuario() {
    }
    /**
     * Constructor con parámetros.
     * @param nombre nombre de usuario.
     * @param username correo electrónico suario.
     * @param password contraseña del usuario.
     * @param rol admin o user.
     * @param edad ead del usuario.
     */
    public Usuario(String nombre, String username, String password, String rol, Integer edad) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.edad = edad;
    }

    /**
     * Método que obtiene el ID del usuario.
     * @return  identificador del usuario.
     */
    public Long getId() {
        return id;
    }
    /**
     * Método que modifica el ID del usuario.
     * @param id nuevo identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Método que obtiene el nombre del usuario.
     * @return nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método que modifica el nombre del usuario.
     * @param nombre nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     *  Método que obtiene el username del usuario.
     * @return username del usuario.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Método que modifica el email del usuario.
     * @param username nuevo email del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     *  Método que obtiene el contraseña del usuario.
     * @return contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Método que modifica el contraseña del usuario.
     * @param password nuevo contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     *  Método que obtiene el rol del usuario.
     * @return rol del usuario.
     */
    public String getRol() {
        return rol;
    }
    /**
     * Método que modifica el rol del usuario.
     * @param rol nuevo rol fr usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    /**
     *  Método que obtiene el edad del usuario.
     * @return edad del usuario.
     */
    public Integer getEdad() {
        return edad;
    }
    /**
     * Método que modifica el edad del usuario.
     * @param edad nuevo edad del usuario.
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
