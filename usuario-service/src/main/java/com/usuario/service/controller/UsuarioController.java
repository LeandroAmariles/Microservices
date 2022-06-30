package com.usuario.service.controller;

import com.usuario.service.entities.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity <List<Carro>> listarCarros(@PathVariable int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Carro> carros = usuarioService.getCarrosporIdUsuario(usuarioId);
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity <List<Moto>> listarMotos(@PathVariable int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotosporIdUsuario(usuarioId);
        return ResponseEntity.ok(motos);
    }
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable int usuarioId, @RequestBody Carro carro){
        Carro nuevoCarro = usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevoCarro);

    }
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable int usuarioId, @RequestBody Moto moto){
        Moto nuevaMoto = usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable int usuarioId){
        Map<String,Object> resultado = usuarioService.getUsuarioAndVehicles(usuarioId);
        return ResponseEntity.ok(resultado);

    }


}
