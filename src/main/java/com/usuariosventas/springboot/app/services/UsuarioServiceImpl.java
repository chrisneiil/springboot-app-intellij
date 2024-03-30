package com.usuariosventas.springboot.app.services;

import com.usuariosventas.springboot.app.models.usuarios.Usuario;
import com.usuariosventas.springboot.app.models.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> findAll() {

        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Integer rutUsuario) {
        return usuarioRepository.findById(rutUsuario);
    }

    @Override
    @Transactional
    public Integer deleteById(Integer rutUsuario) {
        usuarioRepository.deleteById(rutUsuario);
        return rutUsuario;
    }

}
