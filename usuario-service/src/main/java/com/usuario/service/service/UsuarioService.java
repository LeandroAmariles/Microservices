package com.usuario.service.service;

import com.usuario.service.entities.Usuario;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario saveUsuario(Usuario usuario){
        Usuario nuevoUsuario= usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
}
