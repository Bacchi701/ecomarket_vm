package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Cuenta>> listar(){
        List<Cuenta> cuentas = cuentaService.findAll();
        if (cuentas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentas);
    }
    
    @PostMapping
    public ResponseEntity<Cuenta> guardar(@RequestBody Cuenta cuenta){
        Cuenta productoNuevo = cuentaService.save(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> buscar(@PathVariable Integer id){
        try {
            Cuenta cuenta = cuentaService.findById(id);
            return ResponseEntity.ok(cuenta);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Integer id, @RequestBody Cuenta cuenta){
        try {
            Cuenta cue = cuentaService.findById(id);
            cue.setId(id);
            cue.setUsuario(cue.getUsuario());
            cue.setPassword(cue.getPassword());
            cue.setRol(cue.getRol());
            

            cuentaService.save(cue);
            return ResponseEntity.ok(cuenta);
        } catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }

    }
}
