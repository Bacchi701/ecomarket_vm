package com.ecomarket_vm.ecomarket_vm.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Envio { // idProducto, runComprador, fechaCompra, fechaEntrega

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;
    
    @Column(unique=true, length = 13, nullable=false)
    private String runComprador;

    @Column(nullable=false)
    private String fechaCompra;

    @Column(nullable=false)
    private String fechaEntrega;
    
}
