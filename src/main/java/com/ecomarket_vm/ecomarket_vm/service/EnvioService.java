package com.ecomarket_vm.ecomarket_vm.service;

import java.util.Date;
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

    public List<Envio> findByClienteId(Integer idCliente) {
        return envioRepository.findByClienteId(idCliente);
    }

    public List<Envio> findByProductoCodigo(Integer codigoSala) {
        return envioRepository.findByProductoCodigo(codigoSala);
    }

    public List<Envio> findByFechaCompra(Date fecha) {
        return envioRepository.findByFechaCompra(fecha);
    }

    public List<Envio> findByEstado(Integer estado) {
        return envioRepository.findByEstado(estado);
    }

    public long countByClienteId(Integer idCliente) {
        return envioRepository.countByClienteId(idCliente);
    }
}
