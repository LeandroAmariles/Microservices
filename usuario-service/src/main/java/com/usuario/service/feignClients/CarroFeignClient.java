package com.usuario.service.feignClients;

import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="carro-service", url = "http://localhost:8002", path="/carro")
public interface CarroFeignClient {

    @PostMapping
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId")
    public List<Carro> getCarro(@PathVariable int usuarioId);
}
