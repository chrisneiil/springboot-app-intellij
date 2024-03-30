package com.usuariosventas.springboot.app.models.permisos;

import com.usuariosventas.springboot.app.services.PermisoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/permiso")
public class PermisoController {

    @Autowired
    PermisoRepository permisoRepository;
    @GetMapping("/listar-permisos")
    public ResponseEntity<?> traerPermisos() throws SQLException, IOException {
        System.out.println("Listando permisos....");
        return new ResponseEntity<>(permisoRepository.findAll(), HttpStatus.OK);
    }
}
