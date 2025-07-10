package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.service.EnvioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/envio")
public class EnvioController {
    
    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.findAll();
    }

    @GetMapping("/{id}")
    public Envio getEnvioById(@PathVariable Integer id) {
        return envioService.findById(id);
    }
    
    @PostMapping
    public Envio createEnvio(@RequestBody Envio reserva) {
        return envioService.save(reserva);
    }

    @PutMapping("/{id}")
    public Envio updateReserva(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        envio.setRunComprador(envio.getRunComprador());
        envio.setFechaCompra(envio.getFechaCompra());
        envio.setFechaEntrega(envio.getFechaEntrega());
        return envioService.save(envio);
    }

    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Integer id) {
        envioService.deleteById(id);
    }
}