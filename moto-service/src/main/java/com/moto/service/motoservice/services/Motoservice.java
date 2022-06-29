package com.moto.service.motoservice.services;

import com.moto.service.motoservice.entities.Moto;
import com.moto.service.motoservice.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Motoservice {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> obtenerMotos(){
        List<Moto> motos= motoRepository.findAll();
        return motos;
    }
    public Moto obtenerMotoById(int id){
        Moto nuevaMoto= motoRepository.findById(id).orElse(null);
        return nuevaMoto;
    }
    public Moto guardarMoto(Moto moto){
        Moto nuevaMoto=motoRepository.save(moto);
        return nuevaMoto;
    }
    public List<Moto> obtenerMotoPorUsuario(int usuario_id){
        List<Moto> motos= motoRepository.findByUsuarioId(usuario_id);
        return motos;
    }



}
