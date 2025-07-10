package com.ecomarket_vm.ecomarket_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll(){
        return envioRepository.findAll();
    }
    
    public Envio findById(Integer id){
        return envioRepository.findById(id).orElse(null);
    }
    
    public Envio save(Envio envio){
        return envioRepository.save(envio);
    }

    public void deleteById(Integer id){
        envioRepository.deleteById(id);
    }
}
