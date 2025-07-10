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
    private Integer id;
    private String runComprador;
    private Date fechaCompra;
    private Date fechaEntrega;
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

}
