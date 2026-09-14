# Proyecto: Biblioteca Digital UNTEC
## Evaluación del módulo 5: Desarrollo de aplicaciones web dinámicas Java
### Necesidad
La Universidad UNTEC, busca ofrecer a sus estudiantes un sistema de gestión
web para su biblioteca digital. Actualmente, la institución cuenta con un catálogo
manual de libros, y necesita modernizar este sistema para permitir consultas,
préstamos y devoluciones online. Además, desean un entorno visual sencillo que
pueda ser gestionado por el personal de biblioteca sin conocimientos técnicos.
### Objetivo
El propósito del proyecto es diseñar e implementar una aplicación web dinámica
basada en Java EE que permita gestionar libros, usuarios y préstamos de una
biblioteca digital. Esta solución busca resolver las ineficiencias del sistema actual,
asegurando escalabilidad, seguridad y usabilidad.
### Requerimientos
- Utilizar Java EE, JSP, Servlets y patrón MVC.
- Implementar la capa DAO con acceso a base de datos usando JDBC.
- Utilizar JSTL para facilitar la capa de vista.
- Incorporar formularios para interacción del usuario.
- Gestionar sesiones de usuario.
- Desplegar la aplicación en Apache Tomcat usando un archivo .WAR.
- IDE: Eclipse Enterprise Edition.
- Base de datos: MySQL o H2 (uso educativo).
### Aspectos a alidar
- Aplicación del patrón MVC correctamente.
- Uso adecuado de Servlets y JSP.
- Separación de lógica de negocio con DAO.
- Flujo funcional de navegación y gestión de datos.
- Despliegue correcto y funcional en Tomcat.
- Código limpio, comentado y funcional.
- Documentación técnica mínima.
### Entregables
- Proyecto Eclipse .zip con el código fuente.
- Archivo .WAR listo para despliegue.
- Documento README.md con instrucciones de uso.
- Capturas o video funcional de la aplicación corriendo en Tomcat.
### Portafolio
Incorporar este proyecto en el portafolio personal destacando:
- Capturas de pantallas clave del flujo.
- Diagrama MVC utilizado.
- Explicación breve de la arquitectura DAO.
- Enlace al repositorio GitHub (opcional).
- Lecciones aprendidas y tecnologías aplicadas.

# Desarrollo
Nota para la revisión: El proyecto fue desarrollado utilizando un entorno local de MariaDB configurado en el puerto 3307. Esto debido a que en puerto 3306 esta Mysql, y por temas particulares no lo puedo modificar, sin embaego para este trabajo quiero usar MariaDB. Le pido pueda hacer los cambios correspondientes en la configuración.

## Metodología.
1. Creación de proyecto dinámico en IDE Eclipse.
2. Aplicación MVC.
    1. Creación de base de datos y tablas en MariaDB.
    2. Conexión modelo y base de datos MariaDB (JDBC).
    3. Desarrollo de entidades (modelo).
    4. Creación de SERVLET (Controlador).
    5. Desarrollo de JSP (Vista).
    6. Pruebas finales.
## Creación de base de datos y tablas en MariaDB.
- precarga_untec.sql: creación de base de datos, creación de tablas y carga de datos de prueba.
NOTA: esnecesario aplicar este script, antes de activar index.jsp . Se han considerado la creación de dos roles: "admin" con todos los permisos y "lector" con permisos de lectura y solicitud de préstamo.
- La prgarga incluye 2 usuarios para efectos de ingreso y pruebas al sistema. las credenciales son las siguientes:
    - Administrador: user=admin@untec.cl; password=admin
    - Usuario: user=user@email.cl; password=user
## Desarrollo de clases JAVA.
### Conexión
- Conexion.java: Clase que conecta MariaDB con JDBC.
- PruebaSistema.java: Clase para pruebas de funcionamiento de persistencia y consultas con la la base de datos. 
### Modelo
- Libro.java: Clase que gestiona la entidad Libro.
- Prestamo.java: Clase para gestionar entidad prestamo.
- usuario.java: Clase que gestiona la entidad Usuario.
### DAO
- Clase UsuarioDAO: Clase que gestiona sentencias SQL entre modelo y BD untec en MariaDB.
- Clase LibroDAO: Clase que gestiona sentencias SQL entre modelo y BD untec en MariaDB.
- Clase PréstamoDAO: Clase que gestiona sentencias SQL entre modelo y BD untec en MariaDB.
 ## Desarrollo de Servlet.
- UsuarioServlet: controla las peticiones de eliminación, lectura, agregación y actualización de usuarios.
- LibroServlet: controla las peticiones de eliminación, lectura, agregación y actualización de libros.
- PréstamoServlet: Servlet que controla la asignación de libros a lectores y llama a las consultas avanzadas que cruzaron datos con alias de texto, permitiendo filtros avanzados.
- LogoutServlet: implementa la clase para cirerre de sesión de usuario.
## Desarrollo pantallas JSP.
- index.jsp: acceso con credenciales de seguridad (lector y adminsitrador).
- usuario.jsp: opciones CRUD con separación de roles.
- libro.jsp: opciones CRUD con separación de roles.
- prestamo.jsp: opciones CRUD con separación de roles.
# Capturas de pantalla




