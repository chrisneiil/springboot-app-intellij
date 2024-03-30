package com.usuariosventas.springboot.app.services;

import com.usuariosventas.springboot.app.models.permisos.Permiso;
import com.usuariosventas.springboot.app.models.permisos.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    PermisoRepository permisoRepository;
    @Override
    public List<Permiso> findAll() {
        return (List<Permiso>) permisoRepository.findAll();
    }

    @Override
    public Permiso save(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> buscarPorId(Integer idPermiso) {
        return permisoRepository.findById(idPermiso);
    }

}
