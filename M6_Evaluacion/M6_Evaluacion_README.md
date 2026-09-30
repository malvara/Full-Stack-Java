# Proyecto: SpringEduManager
## Evaluación del módulo 6: Desarrollo de aplicaciones JEE con Spring framework
### Problema
Unidad solicitante: Coordinación Académica de un bootcamp de programación. El área académica ha detectado que los estudiantes tienen dificultades para organizar sus cursos, prácticas y evaluaciones en una sola plataforma. Actualmente, utilizan hojas de cálculo y formularios aislados. 
- El equipo técnico debe desarrollar una aplicación web interna que permita a los estudiantes 
    - registrarse, 
    - visualizar sus cursos, 
    - ver sus prácticas y 
    - consultar sus evaluaciones. 
- Esta aplicación servirá también como base para futuros módulos, integrándose con otros sistemas del campus.
### Objetivo
Desarrollar progresivamente una alicación web educativa en Java utilizando el ecosistema de Spring (Spring Boot, Spring MVC, Spring Data JPA, Spring Security, REST) que permita gestionaapr estudiantes, cursos y evaluaciones. El proyecto se realizará en etapas y cada entrega corresponderá a una lección del módulo, asegurando la integración continua del sistema.
### Requerimientos
- Uso de Maven como gestor de dependencias.
- Arquitectura basada en MVC con Spring Boot.
- Persistencia de datos con JPA y H2 o MySQL.
- Seguridad de acceso con Spring Security.
- Exposición de APIs RESTful.
### Aspectos a alidar
1. Estructura y modularización del proyecto según buenas prácticas.
2. Uso adecuado de Maven y confi guración de dependencias.
3. Implementación funcional y estructurada del patrón MVC.
4. Persistencia y manipulación de datos desde base de datos con JPA.
5. Confi guración segura con roles y login/logout.
6. Exposición de servicios RESTful con respuesta en JSON.
7. Coherencia entre entregas y evolución progresiva del proyecto.
### Entregables
1. Repositorio Git con el proyecto completo y documentación (README.md)
2. Evidencias funcionales: capturas de pantalla, video breve, o link a repositorio
3. Proyecto estructurado con:
    - Controladores, servicios, repositorios
    - Seguridad confi gurada
    - APIs REST funcionales
    - Formularios web operativos
### Portafolio
Podrás incorporar este proyecto en tu portafolio como ejemplo de una aplicación web completa con seguridad, base de datos y servicios REST. Se recomienda:
- Documentar en tu GitHub cada etapa del proyecto.
- Incluir en tu CV un enlace directo al proyecto.
- Adjuntar capturas del funcionamiento e indicar tecnologías utilizadas (Maven, Spring Boot, JPA, Spring Security, REST).

---
---

# 🎓 Desarrollo

**SpringEduManager** es un sistema integral de control, coordinación y visualización de rendimiento académico. La aplicación implementa una arquitectura robusta basada en el patrón **MVC (Modelo-Vista-Controlador)** utilizando **Spring Boot 3**, **Spring Security 6**, **Thymeleaf** y persistencia relacional en **MySQL**.

---

## 🛠️ Arquitectura y Tecnologías Utilizadas

*   **Backend:** Java 21, Spring Boot 3.x, Spring Data JPA.
*   **Seguridad:** Spring Security 6, Encriptación criptográfica de contraseñas mediante **BCrypt** y gestión de sesiones mediante `HttpSession`.
*   **Frontend:** Thymeleaf (Motor de plantillas dinámicas), Bootstrap 5 (Diseño responsivo y adaptativo para celulares, tablets y monitores).
*   **Base de Datos:** MySQL 8.x (Estructura relacional con restricciones de llaves foráneas e integridad).

---

## 🔐 Matriz de Control de Accesos por Roles (RBAC)

La plataforma cuenta con un sistema de seguridad estricto que segrega las interfaces y permisos según el perfil del usuario autenticado de forma dinámica:

### 👤 Perfil Coordinador / Administrador (`ROLE_ADMIN`)
*   **CRUD Operativo Completo:** Permisos totales de Creación, Lectura, Actualización (Update) y Eliminación (Delete) en los módulos de Cursos, Usuarios (Alumnos) y Calificaciones.
*   **Seguridad contra Errores:** Botones de borrado protegidos de forma nativa con ventanas de advertencia y confirmación JavaScript en el navegador.
*   **Gestión Académica:** Capacidad de dejar calificaciones en estado "Pendiente" dejando el campo numérico vacío en el formulario.

### 👥 Perfil Estudiante / Alumno (`ROLE_USER`)
*   **Acceso Restringido (Solo Consulta):** Visualización exclusiva de la lista de cursos en los que participa activamente basándose en sus identificadores correlativos.
*   **Links de Rendimiento:** Enlaces directos para auditar de forma aislada sus prácticas y calificaciones por cada asignatura enrolada.
*   **Privatización Estricta de Datos:** El libro de actividades filtra la información por el usuario conectado a la sesión, prohibiendo estrictamente espiar las notas del resto de alumnos.
*   **Catálogo Plano:** Visualización del catálogo institucional sin permisos de alteración ni acceso a la administración de usuarios.

---

## ⚙️ Requisitos e Instalación Local

### 1. Base de Datos (MySQL)
Configure su servidor local de MySQL en el puerto estándar `3306` y cree el esquema de datos:
```sql
CREATE DATABASE edumanager_db;
```

### 2. Archivo de Configuración (`application.properties`)
Asegúrese de contar con las siguientes credenciales de conexión en su entorno:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/edumanager_db?serverTimezone=UTC
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql
spring.jpa.hibernate.ddl-auto=update
```

### 3. Inyección Automática de Datos (DataLoader)
Al arrancar la aplicación por primera vez, el sistema poblará automáticamente la base de datos con registros relacionales de prueba y encriptará las contraseñas base en hashes de BCrypt.

---

## 🔑 Credenciales de Prueba Oficiales

Utilice las siguientes cuentas para auditar los diferentes flujos de la plataforma mediante la Landing Page responsiva de Bienvenida:

*   **Cuenta Administrador:**
    *   **Usuario:** `admin@bootcamp.com`
    *   **Contraseña:** `admin123`
*   **Cuenta Estudiante (Juan):**
    *   **Usuario:** `juan@bootcamp.com`
    *   **Contraseña:** `user123`

---

## 📡 Endpoints del Sistema (APIs REST JSON Públicas)

La plataforma expone los siguientes endpoints para consultas de servicios externos o integraciones:
*   `GET /api/cursos` -> Retorna el listado completo de asignaturas en formato JSON.
*   `GET /api/usuarios` -> Retorna el padrón oficial de usuarios registrados.
*   `GET /api/actividades` -> Retorna el libro global de calificaciones académicas.

