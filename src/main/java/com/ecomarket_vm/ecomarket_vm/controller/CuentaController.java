package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Cuenta;
import com.ecomarket_vm.ecomarket_vm.service.CuentaService;

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
@RequestMapping("/api/cuentas")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con las cuentas de registradas en EcoMarket SPA.")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas", description = "Obtiene una lista de todas las cuentas.")
    public List<Cuenta> getAllCuentas() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cuenta", description = "Obtiene una cuenta mediante su id.")
    public Cuenta getCuentaById(@PathVariable Integer id) {
        return cuentaService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva cuenta", description = "Crea una cuenta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "404", description = "Error al crear la cuenta")
    })
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.save(cuenta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta", description = "Actualiza la información registrada en una cuenta mediante su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "404", description = "Error al actualizar la cuenta")
    })
    public Cuenta updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta) {
        cuenta.setId(id);
        cuenta.setUsuario(cuenta.getUsuario());
        cuenta.setPassword(cuenta.getPassword());
        cuenta.setRol(cuenta.getRol());
        return cuentaService.save(cuenta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta con toda su informacion mediante su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta eliminada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "404", description = "Error al eliminar la cuenta")
    })
    public void deleteCuenta(@PathVariable Integer id) {
        cuentaService.deleteById(id);
    }
}
