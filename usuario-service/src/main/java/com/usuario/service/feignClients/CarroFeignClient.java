package com.usuario.service.feignClients;

import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="carro-service", url = "http://localhost:8002")
public interface CarroFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/carro")
    public Carro save(@RequestBody Carro carro);

    @RequestMapping(method=RequestMethod.GET,value="/usuario/{usuarioId")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
