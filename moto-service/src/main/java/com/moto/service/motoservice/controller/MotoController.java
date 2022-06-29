package com.moto.service.motoservice.controller;

import com.moto.service.motoservice.entities.Moto;
import com.moto.service.motoservice.services.Motoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private Motoservice motoservice;

    @GetMapping
    public ResponseEntity<List<Moto>> listaDeCarros(){
        List<Moto> motos = motoservice.obtenerMotos();
        if(motos.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);

    }
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<Moto>> listaDeCarrosPorIdUsuario(@PathVariable int id_usuario){
        List<Moto> motos = motoservice.obtenerMotoPorUsuario(id_usuario);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerCarroPorId(@PathVariable int id){
        Moto moto= motoservice.obtenerMotoById(id);
        if(moto ==  null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }
    @PostMapping
    public ResponseEntity<Moto> guardarCarro(@RequestBody Moto moto){
        Moto nuevaMoto= motoservice.guardarMoto(moto);
        return ResponseEntity.ok(nuevaMoto);
    }


}
