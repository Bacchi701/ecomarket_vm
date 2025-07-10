package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Cliente;
import com.ecomarket_vm.ecomarket_vm.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        cliente.setRun(cliente.getRun());
        cliente.setNombre(cliente.getNombre());
        cliente.setApellido(cliente.getApellido());
        cliente.setCorreo(cliente.getCorreo());
        cliente.setDireccionEnvio(cliente.getDireccionEnvio());
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
    }
}