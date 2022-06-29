package com.usuario.service.controller;

import com.usuario.service.entities.Usuario;
import com.usuario.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
   public ResponseEntity<List<Usuario>> listarUsuarios(){
       List<Usuario> listaUsuarios = usuarioService.getAll();
       if(listaUsuarios.isEmpty()){
           return ResponseEntity.noContent().build(); // Esta vacia y no hay contenido
       }
       return ResponseEntity.ok(listaUsuarios);
   }
   @GetMapping("/{id}")
   public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario= usuarioService.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build(); // No se encontro el usuario
        }
        return ResponseEntity.ok(usuario);
   }

   @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario= usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
   }
}
