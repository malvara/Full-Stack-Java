-- ============================================================================
-- SCRIPT DE PRECARGA DE DATOS - BIBLIOTECA DIGITAL UNTEC
-- Ejecutar este script en MariaDB para inicializar la base de datos limpia
-- ============================================================================

DROP DATABASE IF EXISTS untec;
CREATE DATABASE untec CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE untec;

-- TABLA: USUARIO (Soporta Roles de Seguridad)
CREATE TABLE usuario (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_nombre VARCHAR(100) NOT NULL,
    usuario_email VARCHAR(100) NOT NULL UNIQUE,
    usuario_password VARCHAR(100) NOT NULL,
    usuario_rol VARCHAR(20) NOT NULL DEFAULT 'lector'
);

-- TABLA: LIBRO
CREATE TABLE libro (
    libro_id INT AUTO_INCREMENT PRIMARY KEY,
    libro_titulo VARCHAR(150) NOT NULL,
    libro_autor VARCHAR(100) NOT NULL,
    libro_copias INT NOT NULL DEFAULT 1,
    libro_tema VARCHAR(50) NOT NULL
);

-- TABLA: PRESTAMO (Relacional / Llaves Foráneas)
CREATE TABLE prestamo (
    prestamo_id INT AUTO_INCREMENT PRIMARY KEY,
    prestamo_libro INT NOT NULL,
    prestamo_usuario INT NOT NULL,
    prestamo_fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (prestamo_libro) REFERENCES libro(libro_id) ON DELETE CASCADE,
    FOREIGN KEY (prestamo_usuario) REFERENCES usuario(usuario_id) ON DELETE CASCADE
);

-- ----------------------------------------------------------------------------
-- INSERCIÓN DE DATOS DE PRUEBA (DATA SEEDING)
-- ----------------------------------------------------------------------------

-- Usuarios Base (1 Administrador y 2 Lectores de ejemplo)
INSERT INTO usuario (usuario_id, usuario_nombre, usuario_email, usuario_password, usuario_rol) VALUES
(1, 'admin', 'admin@untec.cl', 'admin', 'admin'),
(2, 'Valentina Díaz', 'valentina@email.cl', '1234', 'lector'),
(3, 'Carlos Silva', 'carlos@email.com', '5678', 'lector'),
(4,'user',user@email.cl,'user','lector');

-- Catálogo de Libros Iniciales
INSERT INTO libro (libro_id, libro_titulo, libro_autor, libro_copias, libro_tema) VALUES
(1, 'El Principito', 'Antoine de Saint-Exupéry', 5, 'Clásicos'),
(2, '1984', 'George Orwell', 3, 'Ciencia Ficción'),
(3, 'Cien Años de Soledad', 'Gabriel García Márquez', 4, 'Literatura'),
(4, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 2, 'Clásicos'),
(5, 'Breves respuestas a las grandes preguntas', 'Stephen Hawking', 2, 'Ciencia');

-- Registro de Préstamos Activos Cruzados
INSERT INTO prestamo (prestamo_libro, prestamo_usuario, prestamo_fecha) VALUES
(1, 2, '2026-09-01 10:00:00'), -- Valentina tiene prestado 'El Principito'
(2, 2, '2026-09-05 11:30:00'), -- Valentina tiene prestado '1984'
(3, 3, '2026-09-10 15:45:00'); -- Carlos tiene prestado 'Cien Años de Soledad'