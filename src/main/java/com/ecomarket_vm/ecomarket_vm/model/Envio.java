package com.ecomarket_vm.ecomarket_vm.model;

import java.util.Date;
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
    
    @Column(unique = true, length = 13, nullable = false)
    private String runComprador;

    @Column(nullable = false)
    private Date fechaCompra;

    @Column(nullable = false)
    private Date fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;
    
}
