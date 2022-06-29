package com.carro.service.controller;

import com.carro.service.entities.Carro;
import com.carro.service.services.CarroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroServices carroServices;

    @GetMapping
    public ResponseEntity<List<Carro>> listaDeCarros(){
        List<Carro> carros = carroServices.obtenerCarros();
        if(carros.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);

    }
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<Carro>> listaDeCarrosPorIdUsuario(@PathVariable int id_usuario){
        List<Carro> carros = carroServices.obtenerCarroPorUsuario(id_usuario);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarroPorId(@PathVariable int id){
        Carro carro= carroServices.obtenerCarroById(id);
        if(carro ==  null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }
    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
        Carro nuevoCarro= carroServices.guardarCarro(carro);
        return ResponseEntity.ok(nuevoCarro);
    }

}
