package com.usuariosventas.springboot.app.services;

import com.usuariosventas.springboot.app.models.permisos.Permiso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PermisoService {
    List<Permiso> findAll();

    Permiso save(Permiso permiso);

    Optional<Permiso> buscarPorId(Integer idPermiso);
}
