package com.usuario.service.service;

import com.usuario.service.entities.Usuario;
import com.usuario.service.feignClients.CarroFeignClient;
import com.usuario.service.feignClients.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

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

    /***
     * Usando RestTemplate y @FeignClient para comunicar los microservicios, ambos son diferentes, hacen lo mismo, el mas usado
     * es el RestTemplate
     * @return
     */
    public List<Carro> getCarrosporIdUsuario(int usuarioId){
        List<Carro> carros= restTemplate.getForObject("http://localhost:8002/carro/usuario/"+ usuarioId, List.class);
        return carros;
    }

    public List<Carro> obtenerCarroporIdUsuario(int usuarioId){
        List<Carro> carros = carroFeignClient.getCarro(usuarioId);
        return carros;
    }

    public Carro saveCarro( int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return carro;
    }
    public List<Moto> getMotosporIdUsuario(int usuarioId){
        List<Moto> motos= restTemplate.getForObject("http://localhost:8003/moto/usuario/"+ usuarioId, List.class);
        return motos;
    }
    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }
    public Map<String, Object> getUsuarioAndVehicles(int usuarioId){
        Map<String,Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);
        List<Carro> carros = carroFeignClient.getCarro(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros", "El usuario no tiene carros");
        }else {
            resultado.put("Carros",carros);
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos","El usuario no tiene motos");
        }else{
            resultado.put("Motos", motos);
        }
        return resultado;
    }

}
