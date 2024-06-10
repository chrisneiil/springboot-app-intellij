package com.usuariosventas.springboot.app.services;

import com.usuariosventas.springboot.app.models.usuarios.Usuario;
import com.usuariosventas.springboot.app.models.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {

        Optional<Usuario> userOptional = usuarioRepository.findById(Integer.parseInt(rut));

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", rut));
        }

        Usuario user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getPermisos().stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getPermiso()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getRut().toString(),
                user.getPassword(),
                user.isEnable(),
                true,
                true,
                true,
                authorities
                );
    }
}
