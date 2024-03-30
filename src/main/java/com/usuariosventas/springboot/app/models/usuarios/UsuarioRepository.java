package com.usuariosventas.springboot.app.models.usuarios;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


    Optional<Usuario> findById(Integer rut);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    void deleteById(Integer rutUsuario);
}
