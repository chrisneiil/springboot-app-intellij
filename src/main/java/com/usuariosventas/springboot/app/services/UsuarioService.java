package com.usuariosventas.springboot.app.services;

import com.usuariosventas.springboot.app.models.usuarios.Usuario;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Integer rutUsuario);

    Integer deleteById(Integer rutUsuario);
}
