package com.usuariosventas.springboot.app.models.permisos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.usuariosventas.springboot.app.models.usuarios.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.IdGeneratorType;

import java.util.List;

@Entity
@Table(name = "permisos")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "permiso", unique = true)
    private String permiso;

    @JsonIgnoreProperties({"permisos", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "permisos")
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return "Permiso{" +
                "idPermiso=" + idPermiso +
                ", permiso='" + permiso + '\'' +
                '}';
    }
}
