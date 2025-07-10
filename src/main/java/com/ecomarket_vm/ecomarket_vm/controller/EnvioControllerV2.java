package com.ecomarket_vm.ecomarket_vm.controller;

import com.ecomarket_vm.ecomarket_vm.assemblers.EnvioModelAssembler;
import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.service.EnvioService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/envios")
@Tag(name = "Envios v2", description = "HATEOAS")
public class EnvioControllerV2 {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssembler assembler;

    // Método personalizado para obtener envios por cliente
    @GetMapping(value = "/cliente/{idCliente}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getEnviosByCliente(@PathVariable Integer idCliente) {
        List<EntityModel<Envio>> envios = envioService.findByClienteId(idCliente).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getEnviosByCliente(idCliente)).withSelfRel());
    }
    
    // Método personalizado para obtener envios por producto
    @GetMapping(value = "/producto/{codigoProducto}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getEnviosByProducto(@PathVariable Integer codigoProducto) {
        List<EntityModel<Envio>> envios = envioService.findByProductoCodigo(codigoProducto).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getEnviosByProducto(codigoProducto)).withSelfRel());
    }

    // Método personalizado para obtener Envios por fecha de compra
    @GetMapping(value = "/fecha/{fecha}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getEnviosByFecha(@PathVariable Date fecha) {
        List<EntityModel<Envio>> envios = envioService.findByFechaCompra(fecha).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getEnviosByFecha(fecha)).withSelfRel());
    }

    // Método personalizado para obtener Envios por estado
    @GetMapping(value = "/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getEnviosByEstado(@PathVariable Integer estado) {
        List<EntityModel<Envio>> envios = envioService.findByEstado(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getEnviosByEstado(estado)).withSelfRel());
    }

    // Método personalizado para obtener el total de Envios de un cliente
    @GetMapping(value = "/cliente/{idCliente}/total", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Long> getTotalEnviosByCliente(@PathVariable Integer idCliente) {
        long totalEnvios = envioService.countByClienteId(idCliente);
        return EntityModel.of(totalEnvios,
                linkTo(methodOn(EnvioControllerV2.class).getTotalEnviosByCliente(idCliente)).withSelfRel());
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> getEnvioById(@PathVariable Integer id) {
        Envio envio = envioService.findById(id);
        return assembler.toModel(envio);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        Envio newEnvio = envioService.save(envio);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).getEnvioById(newEnvio.getId())).toUri())
                .body(assembler.toModel(newEnvio));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.save(envio);
        return ResponseEntity.ok(assembler.toModel(updatedEnvio));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEnvio(@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
