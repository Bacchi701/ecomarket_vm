package com.ecomarket_vm.ecomarket_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket_vm.ecomarket_vm.model.Cuenta;
import com.ecomarket_vm.ecomarket_vm.repository.CuentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> findAll(){
        return cuentaRepository.findAll();
    }

    public Cuenta findById(Integer id){
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta save(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public void deleteById(Integer id){
        cuentaRepository.deleteById(id);
    }
}
