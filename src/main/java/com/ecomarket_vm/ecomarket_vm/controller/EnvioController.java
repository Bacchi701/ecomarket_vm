package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas con los envios de EcoMarket SPA")
public class EnvioController {
    
    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener todos los envios", description = "Obtiene una lista de todos los envios generados.")
    public List<Envio> getAllEnvios() {
        return envioService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envio", description = "Obtiene un envio mediante su id.")
    public Envio getEnvioById(@PathVariable Integer id) {
        return envioService.findById(id);
    }
    
    @PostMapping
    @Operation(summary = "Crear un envio", description = "Crea un envio con su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "404", description = "Error al crear el envio")
    })
    public Envio createEnvio(@RequestBody Envio reserva) {
        return envioService.save(reserva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envio", description = "Actualiza un envio con su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "404", description = "Error al actualizar el envio")
    })
    public Envio updateReserva(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        envio.setRunComprador(envio.getRunComprador());
        envio.setFechaCompra(envio.getFechaCompra());
        envio.setFechaEntrega(envio.getFechaEntrega());
        return envioService.save(envio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envio", description = "Elimina un envio mediante su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio eliminado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "404", description = "Error al eliminar el envio")
    })
    public void deleteEnvio(@PathVariable Integer id) {
        envioService.deleteById(id);
    }
}