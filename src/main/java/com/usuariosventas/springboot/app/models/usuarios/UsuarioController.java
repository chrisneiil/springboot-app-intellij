package com.usuariosventas.springboot.app.models.usuarios;

import com.usuariosventas.springboot.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/public/listar-usuarios")
    public ResponseEntity<?> traerListaUsuarios() throws SQLException, IOException {
        System.out.println("LISTANDO USUARIOS....");
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insertar-usuario")
    public ResponseEntity<?> insertarNuevoUsuario(@RequestBody Usuario usuario) throws SQLException, IOException {
        System.out.println();
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.OK);
    }
}
