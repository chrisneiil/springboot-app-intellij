package com.usuariosventas.springboot.app.models.permisos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PermisoRepository extends CrudRepository<Permiso, Integer> {
    List<Permiso> findAll();

    Permiso save(Permiso permiso);


    Optional<Permiso> findByPermiso(String permiso);
    Optional<Permiso> findById(Integer idPermiso);

}
