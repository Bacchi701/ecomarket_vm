package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Cliente;
import com.ecomarket_vm.ecomarket_vm.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes de EcoMarket SPA.")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
     @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes.")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente", description = "Obtiene un cliente mediante su id.")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear a un nuevo cliente", description = "Agrega a un cliente.")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar a un cliente", description = "Actualiza la información personal de un cliente mediante su id.")
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
    @Operation(summary = "Eliminar a un cliente", description = "Elimina al cliente mediante su id.")
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
    }
}