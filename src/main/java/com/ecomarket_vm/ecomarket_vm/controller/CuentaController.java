package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Cuenta;
import com.ecomarket_vm.ecomarket_vm.service.CuentaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public Cuenta getCuentaById(@PathVariable Integer id) {
        return cuentaService.findById(id);
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.save(cuenta);
    }

    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta) {
        cuenta.setId(id);
        cuenta.setUsuario(cuenta.getUsuario());
        cuenta.setPassword(cuenta.getPassword());
        cuenta.setRol(cuenta.getRol());
        return cuentaService.save(cuenta);
    }

    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Integer id) {
        cuentaService.deleteById(id);
    }
}
