package com.carro.service.services;

import com.carro.service.CarroServiceApplication;
import com.carro.service.entities.Carro;
import com.carro.service.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServices {

    @Autowired
    public CarroRepository carroRepository;

    public List<Carro> obtenerCarros(){
        List<Carro> carros= carroRepository.findAll();
        return carros;
    }
    public Carro obtenerCarroById(int id){
        Carro nuevoCarro= carroRepository.findById(id).orElse(null);
        return nuevoCarro;
    }
    public Carro guardarCarro(Carro carro){
        Carro nuevoCarro=carroRepository.save(carro);
        return nuevoCarro;
    }
    public List<Carro> obtenerCarroPorUsuario(int usuario_id){
        List<Carro> carros= carroRepository.findByUsuarioId(usuario_id);
        return carros;
    }
}
