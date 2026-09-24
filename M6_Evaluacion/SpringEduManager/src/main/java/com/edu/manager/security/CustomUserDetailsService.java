package com.edu.manager.security;

import com.edu.manager.model.Usuario;
import com.edu.manager.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    /**
     * Inyección del repositorio de usuarios
     * @param usuarioRepository
     */
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /**
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al usuario en MySQL usando el correo electrónico que ingresó en el formulario
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));
        // Traducimos nuestro objeto Usuario al formato interno estructurado que exige Spring Security
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // Aquí leerá la clave (encriptada más adelante)
                .roles(usuario.getRol().replace("ROLE_", "")) // Remueve el prefijo ROLE_ porque .roles() lo añade solo
                .build();
    }
}
