package com.edu.manager.security;

import com.edu.manager.model.Usuario;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession; // Importación obligatoria para capturar la sesión

import java.util.Collections;

/**
 * Este componente actuará como el "investigador" de Spring Security. Cuando un usuario intente
 * loguearse poniendo su correo, esta clase irá a buscarlo al UsuarioRepository en MySQL y le
 * entregará sus datos y roles al motor de seguridad.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    /**
     * Inyección del repositorio de usuarios.
     * @param usuarioRepository objeto que sirve para operar con MySQL.
     */
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /**
     *Método puente entre la base de datos MySQL y el motor de autenticación de Spring Security
     * @param username correo eléctronnico del usuario.
     * @return buscar al usuario y retornar un objeto compatible con el framework. Si el usuario no es encontrado,
     * el método genera una excepción de tipo UsernameNotFoundException
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos al usuario real en MySQL usando su correo electrónico
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con cuenta: " + username));

        // 2. CAPTURA CRÍTICA: Tomamos la sesión HTTP del navegador web
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        // 3. Guardamos el objeto COMPLETO de MySQL (que incluye su ID real = 2) en la sesión
        session.setAttribute("usuariosession", usuario);

        // 4. Retornamos las credenciales nativas para que Spring Security procese la clave criptográfica
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }
}
