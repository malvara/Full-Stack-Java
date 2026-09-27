package com.edu.manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // <-- Importación obligatoria
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Define los permisos por rol, configura el ciclo de Login/Logout y activa el Encriptador.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * Definición del codificador de contraseñas
     * @return activación del motor de encriptación oficial y más seguro de Spring Security para
     * proteger las contraseñas de los usuarios.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Forzamos el encriptador dentro del constructor o método de asignación directa
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // <-- Esto amarra de forma estricta BCrypt con tu MySQL
        return authProvider;
    }
    /**
     * Filtro de seguridad que define las reglas de acceso a las URLs.
     * @param http objeto de Spring que diseña o configura de las reglas de seguridad.
     * @return objeto de tipo SecurityFilterChain (Cadena de Filtros de Seguridad).
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivamos CSRF solo de forma temporal para facilitar tus pruebas con los formularios y APIs REST locales
                .csrf(csrf -> csrf.disable())
                // Configuración de Autorizaciones en las URLs
                .authorizeHttpRequests(auth -> auth
                        // Las librerías de estilos CSS de Bootstrap y las APIs JSON públicas se permiten para todos
                        .requestMatchers("/css/**", "/js/**", "/api/**").permitAll()
                        // Regla estricta para el rol ADMIN: Guardar cursos requiere privilegios elevados
                        .requestMatchers("/cursos/guardar").hasRole("ADMIN")
                        .requestMatchers("/cursos/guardar", "/cursos/editar/**").hasRole("ADMIN")
                        // Cualquier otra pantalla web tradicional (/cursos, /actividades, /usuarios) requiere estar logueado
                        .anyRequest().authenticated()
                )
                // Modifica únicamente este bloque dentro de tu SecurityConfig.java
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/cursos", true) // Al loguearse con éxito, obliga a ir a la tabla de cursos
                        .permitAll()
                )
                // Configuración del Logout (Cierre de Sesión seguro)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                );
        return http.build();
    }
    //@Bean
    //public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        //return config.getAuthenticationManager();
    //}
}
