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

public class Envio { // idEnvio, runComprador, fechaCompra, fechaEntrega

    @Id
    private Integer idEnvio;
    private String runComprador;
    private Date fechaCompra;
    private Date fechaEntrega;

    @OneToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;

}
