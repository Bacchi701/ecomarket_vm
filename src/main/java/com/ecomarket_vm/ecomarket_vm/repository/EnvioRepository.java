package com.ecomarket_vm.ecomarket_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomarket_vm.ecomarket_vm.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    
}
