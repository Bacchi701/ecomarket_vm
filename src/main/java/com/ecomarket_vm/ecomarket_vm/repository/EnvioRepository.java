package com.ecomarket_vm.ecomarket_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomarket_vm.ecomarket_vm.model.Envio;

import java.util.Date;
import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    List<Envio> findByClienteId(Integer idCliente);

    List<Envio> findByProductoCodigo(Integer codigoProducto);

    List<Envio> findByFechaCompra(Date fecha);

    List<Envio> findByEstado(Integer estado);

    long countByClienteId(Integer idCliente);

    
}
